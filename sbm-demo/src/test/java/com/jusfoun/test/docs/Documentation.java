package com.jusfoun.test.docs;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.alibaba.fastjson.JSON;
import com.jusfoun.entity.TArea;

import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.GroupBy;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import springfox.documentation.staticdocs.SwaggerResultHandler;

@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@RunWith(SpringRunner.class)
@SpringBootTest
public class Documentation {

	private String snippetDir = "target/generated-snippets";
	private String outputDir = "target/asciidoc";
	// private String indexDoc = "docs/asciidoc/index.adoc";

	@Autowired
	private MockMvc mockMvc;

	@After
	public void Test() throws Exception {
		// 得到swagger.json,写入outputDir目录中
		mockMvc.perform(get("/v2/api-docs").accept(MediaType.APPLICATION_JSON)).andDo(SwaggerResultHandler.outputDirectory(outputDir).build()).andExpect(status().isOk())
				.andReturn();

		// 读取上一步生成的swagger.json转成asciiDoc,写入到outputDir
		// 这个outputDir必须和插件里面<generated></generated>标签配置一致
		Swagger2MarkupConverter.from(outputDir + "/swagger.json").withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
				.withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
				.withExamples(snippetDir).build().intoFolder(outputDir);// 输出

	}

	@Test
	public void TestApi() throws Exception {
		mockMvc.perform(get("/area")//
				.header("Authorization",
						"bearer eyJhbGciOiJIUzUxMiJ9.eyJzY29wZXMiOlsidHJ1c3QiLCJyZWFkIiwid3JpdGUiXSwic3ViIjoiYWRtaW4iLCJpc3MiOiJ3ZWJfY2xpZW50IiwiaWF0IjoxNTQxNzI5MzQzLCJleHAiOjE1NDE3MzY1NDN9.1TMNCzW5P27THeucs90Pck93ZNOmzpXdAoqIG0ZmTtjfaNeOhVpapCPm-hmn9-Z39UueQU5HXzv6-r6NXrkHKQ")//
				.param("id", "1")//
				.accept(MediaType.APPLICATION_JSON))//
				.andExpect(status().isOk())//
				.andDo(MockMvcRestDocumentation.document("infoById", preprocessResponse(prettyPrint())));

		TArea area = new TArea();
		area.setId(100000L);
		area.setAreaName("北京市");
		area.setParentId(0L);
		area.setParentName("中国");
		area.setLevel((byte) 1);
		area.setLeaf(false);

		mockMvc.perform(post("/area")//
				.contentType(MediaType.APPLICATION_JSON)//
				.content(JSON.toJSONString(area))//
				.accept(MediaType.APPLICATION_JSON))//
				.andExpect(status().is2xxSuccessful())//
				.andDo(MockMvcRestDocumentation.document("save", preprocessResponse(prettyPrint())));
	}

}
