<?xml version="1.0"?>
<recipe>

	<instantiate from="src/app_package/NodeHolder.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${className}HodeHolder.kt" />

  <instantiate from="res/layout/fragment_blank.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${xmlName}.xml" />

	<open file="${srcOut}/${className}HodeHolder.kt"/>
</recipe>
