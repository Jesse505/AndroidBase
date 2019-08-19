<?xml version="1.0"?>
<#import "root://activities/common/kotlin_macros.ftl" as kt>

<recipe>

    <merge from="root/AndroidManifest.xml.ftl"
        to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    <@kt.addAllKotlinDependencies />
    <instantiate from="root/src/app_package/Activity.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${activityName}Activity.${ktOrJavaExt}" />
    <instantiate from="root/src/app_package/Contract.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/contract/${contractName}Contract.${ktOrJavaExt}" />
    <instantiate from="root/src/app_package/Presenter.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/presenter/${presenterName}Presenter.${ktOrJavaExt}" />
    <instantiate from="root/src/app_package/Model.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/model/${modelName}Model.${ktOrJavaExt}" />
    <#include "../common/recipe_simple.xml.ftl" />
    <open file="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />
    <open file="${escapeXmlAttribute(srcOut)}/${activityName}Activity.${ktOrJavaExt}" />
    <open file="${escapeXmlAttribute(resOut)}/layout/${activityLayout}.xml" />
    <open file="${escapeXmlAttribute(srcOut)}/contract/${contractName}Contract.${ktOrJavaExt}" />
    <open file="${escapeXmlAttribute(srcOut)}/presenter/${presenterName}Presenter.${ktOrJavaExt}" />
    <open file="${escapeXmlAttribute(srcOut)}/model/${modelName}Model.${ktOrJavaExt}" />


</recipe>