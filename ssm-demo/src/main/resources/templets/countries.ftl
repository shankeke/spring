<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:o="urn:schemas-microsoft-com:office:office"
 xmlns:x="urn:schemas-microsoft-com:office:excel"
 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:html="http://www.w3.org/TR/REC-html40">
 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <Created>2015-06-05T18:19:34Z</Created>
  <LastSaved>2018-07-24T10:10:46Z</LastSaved>
  <Version>16.00</Version>
 </DocumentProperties>
 <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
  <AllowPNG/>
  <RemovePersonalInformation/>
 </OfficeDocumentSettings>
 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
  <WindowHeight>12648</WindowHeight>
  <WindowWidth>22260</WindowWidth>
  <WindowTopX>32767</WindowTopX>
  <WindowTopY>32767</WindowTopY>
  <ProtectStructure>False</ProtectStructure>
  <ProtectWindows>False</ProtectWindows>
 </ExcelWorkbook>
 <Styles>
  <Style ss:ID="Default" ss:Name="Normal">
   <Alignment ss:Vertical="Bottom"/>
   <Borders/>
   <Font ss:FontName="等线" x:CharSet="134" ss:Size="11" ss:Color="#000000"/>
   <Interior/>
   <NumberFormat/>
   <Protection/>
  </Style>
  <Style ss:ID="s64">
   <Alignment ss:Horizontal="Center" ss:Vertical="Bottom"/>
  </Style>
  <Style ss:ID="s70">
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
  </Style>
  <Style ss:ID="s71">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
   <NumberFormat ss:Format="@"/>
  </Style>
  <Style ss:ID="s72">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
  </Style>
  <Style ss:ID="s73">
   <Alignment ss:Horizontal="Center" ss:Vertical="Bottom"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
  </Style>
 </Styles>
 <Worksheet ss:Name="国家地区">
  <Table ss:ExpandedColumnCount="4" ss:ExpandedRowCount="${total.rowCount+99}" x:FullColumns="1"
   x:FullRows="1" ss:DefaultRowHeight="13.8">
   <Column ss:StyleID="s64" ss:AutoFitWidth="0" ss:Width="64.2"/>
   <Column ss:AutoFitWidth="0" ss:Width="83.4"/>
   <Column ss:AutoFitWidth="0" ss:Width="124.2"/>
   <Column ss:AutoFitWidth="0" ss:Width="135.6"/>
   <Row>
    <Cell ss:StyleID="s72"><Data ss:Type="String">首字母</Data></Cell>
    <Cell ss:StyleID="s73"><Data ss:Type="String">简称</Data></Cell>
    <Cell ss:StyleID="s73"><Data ss:Type="String">全称</Data></Cell>
    <Cell ss:StyleID="s73"><Data ss:Type="String">英文名称</Data></Cell>
   </Row>
   <#if total.list?? && (total.list?size > 0)>
   <#list total.list as item>
   <#if item.list?? && (item.list?size>0)>
   <#list item.list as secItem>
   <Row>
   <#if secItem_index == 0>
    <Cell ss:MergeDown="${item.rowCount-1}" ss:StyleID="s71"><Data ss:Type="String">${item.alpha!''}</Data></Cell>
   </#if>
    <Cell ss:Index="2" ss:StyleID="s70"><Data ss:Type="String">${secItem.shName!''}</Data></Cell>
    <Cell ss:StyleID="s70"><Data ss:Type="String">${secItem.zhName!''}</Data></Cell>
    <Cell ss:StyleID="s70"><Data ss:Type="String">${secItem.enName!''}</Data></Cell>
   </Row>
   </#list>
   </#if>
   </#list>
   </#if>
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.3"/>
    <Footer x:Margin="0.3"/>
    <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
   </PageSetup>
   <Print>
    <ValidPrinterInfo/>
    <PaperSizeIndex>9</PaperSizeIndex>
    <HorizontalResolution>600</HorizontalResolution>
    <VerticalResolution>600</VerticalResolution>
   </Print>
   <Selected/>
   <Panes>
    <Pane>
     <Number>3</Number>
     <ActiveCol>2</ActiveCol>
    </Pane>
   </Panes>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
</Workbook>
