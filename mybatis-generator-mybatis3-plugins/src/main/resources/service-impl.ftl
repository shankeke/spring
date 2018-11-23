package ${targetPackage}.impl;
 
import ${entityPackage}.${EntityName};
import ${injectPackage}.${EntityName}Mapper;
import ${targetPackage}.${EntityName}Service;
import com.jusfoun.common.mybatis.mapper.MyBaseMapper;
import com.jusfoun.common.mybatis.mapper.MyIdableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
/**
*  generated by ServiceGeneratorPlugin.java
*/
@Service
public class ${EntityName}ServiceImpl implements ${EntityName}Service{
	
	@Autowired
	private ${EntityName}Mapper ${entityName}Mapper;
	
	@Override
	public MyBaseMapper<${EntityName}> getMyBaseMapper() {
		return ${entityName}Mapper;
	}
	
	@Override
	public MyIdableMapper<${EntityName}> getMyIdableMapper() {
		return ${entityName}Mapper;
	}
 
}
