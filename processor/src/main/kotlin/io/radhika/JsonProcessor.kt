@file:OptIn(KspExperimental::class)

package io.radhika

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.gson.annotations.SerializedName

@KspExperimental
class JsonProcessor(private val codeGenerator: CodeGenerator) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(JsonSerializable::class.qualifiedName!!)

        symbols.forEach { symbol ->
            if (symbol is KSClassDeclaration) {
                generateJsonParser(symbol)
            }
        }
        return emptyList()
    }

    private fun generateJsonParser(classDeclaration: KSClassDeclaration) {
        val className = classDeclaration.simpleName.asString()
        val packageName = classDeclaration.packageName.asString()
        val properties = classDeclaration.getAllProperties()

        val file = codeGenerator.createNewFile(Dependencies.ALL_FILES, packageName, "${className}JsonParser")
        file.bufferedWriter().use { writer ->
            writer.appendLine("package $packageName")
            writer.appendLine("import org.json.JSONObject")
            writer.appendLine("")
            writer.appendLine("fun ${className}.Companion.fromJson(jsonString: String): $className {")
            writer.appendLine("    val jsonObject = JSONObject(jsonString)")
            writer.appendLine("    return $className(")

            properties.forEach { prop ->
                val serializedName = prop.getAnnotationsByType(SerializedName::class).firstOrNull()?.value
                val propName = serializedName ?: prop.simpleName.asString()

                val jsonMethod = when (prop.type.toString()) {
                    "String" -> "getString"
                    "Int" -> "getInt"
                    "Double" -> "getDouble"
                    "Boolean" -> "getBoolean"
                    else -> "opt"
                }
                writer.appendLine("        jsonObject.$jsonMethod(\"$propName\"),")
            }

            writer.appendLine("    )")
            writer.appendLine("}")
        }
    }
}
