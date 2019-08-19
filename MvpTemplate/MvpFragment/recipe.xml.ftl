<?xml version="1.0"?>
<#import "root://activities/common/kotlin_macros.ftl" as kt>

<recipe>

    <@kt.addAllKotlinDependencies />
    <instantiate from="root/src/app_package/Fragment.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${activityName}Fragment.${ktOrJavaExt}" />
    <instantiate from="root/src/app_package/Contract.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/contract/${contractName}Contract.${ktOrJavaExt}" />
    <instantiate from="root/src/app_package/Presenter.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/presenter/${presenterName}Presenter.${ktOrJavaExt}" />
    <instantiate from="root/src/app_package/Model.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/model/${modelName}Model.${ktOrJavaExt}" />
    <instantiate from="root/res/fragment.xml.ftl"
            to="${escapeXmlAttribute(resOut)}/layout/${activityLayout}.xml" />

    <open file="${escapeXmlAttribute(srcOut)}/${activityName}Fragment.${ktOrJavaExt}" />
    <open file="${escapeXmlAttribute(resOut)}/layout/${activityLayout}.xml" />
    <open file="${escapeXmlAttribute(srcOut)}/contract/${contractName}Contract.${ktOrJavaExt}" />
    <open file="${escapeXmlAttribute(srcOut)}/presenter/${presenterName}Presenter.${ktOrJavaExt}" />
    <open file="${escapeXmlAttribute(srcOut)}/model/${modelName}Model.${ktOrJavaExt}" />


</recipe>