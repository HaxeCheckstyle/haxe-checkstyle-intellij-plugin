package haxe.checkstyle;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.io.*;

public class Config extends AnAction {

    // TODO: Get the json from the plugin folder or load it from haxelib folder
    private String json = "{\n" +
	    "    \"defineCombinations\": [],\n" +
	    "    \"defaultSeverity\": \"INFO\",\n" +
	    "    \"baseDefines\": [],\n" +
	    "    \"exclude\": {},\n" +
	    "    \"checks\": [\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"Anonymous\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"spaceBefore\": false,\n" +
	    "\t\t\"spaceInside\": false,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"ArrayAccess\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"ArrayLiteral\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"AvoidInlineConditionals\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"AvoidStarImport\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"format\": \"^(e|t|ex|[a-z][a-z][a-zA-Z]+)$\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"CatchParameterName\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"ignoreExtern\": true,\n" +
	    "\t\t\"format\": \"^[A-Z][A-Z0-9]*(_[A-Z0-9_]+)*$\",\n" +
	    "\t\t\"tokens\": [],\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"ConstantName\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"thresholds\": [\n" +
	    "\t\t    {\n" +
	    "\t\t\t\"complexity\": 20,\n" +
	    "\t\t\t\"severity\": \"WARNING\"\n" +
	    "\t\t    },\n" +
	    "\t\t    {\n" +
	    "\t\t\t\"complexity\": 25,\n" +
	    "\t\t\t\"severity\": \"ERROR\"\n" +
	    "\t\t    }\n" +
	    "\t\t],\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"CyclomaticComplexity\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"DefaultComesLast\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"Dynamic\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"ERegLiteral\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"tokens\": [\n" +
	    "\t\t    \"CLASS_DEF\",\n" +
	    "\t\t    \"ENUM_DEF\",\n" +
	    "\t\t    \"ABSTRACT_DEF\",\n" +
	    "\t\t    \"TYPEDEF_DEF\",\n" +
	    "\t\t    \"INTERFACE_DEF\",\n" +
	    "\t\t    \"OBJECT_DECL\",\n" +
	    "\t\t    \"FUNCTION\",\n" +
	    "\t\t    \"FOR\",\n" +
	    "\t\t    \"IF\",\n" +
	    "\t\t    \"WHILE\",\n" +
	    "\t\t    \"SWITCH\",\n" +
	    "\t\t    \"TRY\",\n" +
	    "\t\t    \"CATCH\"\n" +
	    "\t\t],\n" +
	    "\t\t\"option\": \"empty\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"EmptyBlock\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"requireEmptyLineAfterPackage\": true,\n" +
	    "\t\t\"requireEmptyLineAfterInterface\": true,\n" +
	    "\t\t\"requireEmptyLineAfterAbstract\": true,\n" +
	    "\t\t\"allowEmptyLineAfterSingleLineComment\": true,\n" +
	    "\t\t\"max\": 1,\n" +
	    "\t\t\"requireEmptyLineAfterClass\": true,\n" +
	    "\t\t\"allowEmptyLineAfterMultiLineComment\": true,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"EmptyLines\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"enforceEmptyPackage\": false,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"EmptyPackage\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"max\": 2000,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"FileLength\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"option\": \"upperCase\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"HexadecimalLiteral\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"ignoreSetter\": true,\n" +
	    "\t\t\"ignoreFormat\": \"^(main|run)$\",\n" +
	    "\t\t\"ignoreConstructorParameter\": true,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"HiddenField\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"character\": \"tab\",\n" +
	    "\t\t\"ignorePattern\": \"^$\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"IndentationCharacter\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"ignoreReturnAssignments\": false,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"InnerAssignment\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"allowProperties\": false,\n" +
	    "\t\t\"allowMarkerInterfaces\": true,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"Interface\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"tokens\": [\n" +
	    "\t\t    \"CLASS_DEF\",\n" +
	    "\t\t    \"ENUM_DEF\",\n" +
	    "\t\t    \"ABSTRACT_DEF\",\n" +
	    "\t\t    \"TYPEDEF_DEF\",\n" +
	    "\t\t    \"INTERFACE_DEF\",\n" +
	    "\t\t    \"FUNCTION\",\n" +
	    "\t\t    \"FOR\",\n" +
	    "\t\t    \"IF\",\n" +
	    "\t\t    \"WHILE\",\n" +
	    "\t\t    \"SWITCH\",\n" +
	    "\t\t    \"TRY\",\n" +
	    "\t\t    \"CATCH\"\n" +
	    "\t\t],\n" +
	    "\t\t\"ignoreEmptySingleline\": true,\n" +
	    "\t\t\"option\": \"eol\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"LeftCurly\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"max\": 160,\n" +
	    "\t\t\"ignorePattern\": \"^$\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"LineLength\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"listeners\": [\n" +
	    "\t\t    \"addEventListener\",\n" +
	    "\t\t    \"addListener\",\n" +
	    "\t\t    \"on\",\n" +
	    "\t\t    \"once\"\n" +
	    "\t\t],\n" +
	    "\t\t\"format\": \"^_?[a-z][a-zA-Z0-9]*$\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"ListenerName\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"ignoreExtern\": true,\n" +
	    "\t\t\"format\": \"^[a-z][a-zA-Z0-9]*$\",\n" +
	    "\t\t\"tokens\": [],\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"LocalVariableName\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"ignoreNumbers\": [\n" +
	    "\t\t    -1,\n" +
	    "\t\t    0,\n" +
	    "\t\t    1,\n" +
	    "\t\t    2\n" +
	    "\t\t],\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"MagicNumber\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"ignoreExtern\": true,\n" +
	    "\t\t\"format\": \"^[a-z][a-zA-Z0-9]*$\",\n" +
	    "\t\t\"tokens\": [],\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"MemberName\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"maxPrivate\": 100,\n" +
	    "\t\t\"maxPublic\": 100,\n" +
	    "\t\t\"severity\": \"INFO\",\n" +
	    "\t\t\"maxTotal\": 100\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"MethodCount\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"max\": 50,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"MethodLength\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"ignoreExtern\": true,\n" +
	    "\t\t\"format\": \"^[a-z][a-zA-Z0-9]*$\",\n" +
	    "\t\t\"tokens\": [],\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"MethodName\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"modifiers\": [\n" +
	    "\t\t    \"MACRO\",\n" +
	    "\t\t    \"OVERRIDE\",\n" +
	    "\t\t    \"PUBLIC_PRIVATE\",\n" +
	    "\t\t    \"STATIC\",\n" +
	    "\t\t    \"INLINE\",\n" +
	    "\t\t    \"DYNAMIC\"\n" +
	    "\t\t],\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"ModifierOrder\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"minLength\": 2,\n" +
	    "\t\t\"ignore\": \"^\\\\s+$\",\n" +
	    "\t\t\"allowDuplicates\": 2,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"MultipleStringLiterals\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"MultipleVariableDeclarations\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"allowSingleLineStatement\": true,\n" +
	    "\t\t\"tokens\": [\n" +
	    "\t\t    \"FOR\",\n" +
	    "\t\t    \"IF\",\n" +
	    "\t\t    \"ELSE_IF\",\n" +
	    "\t\t    \"WHILE\",\n" +
	    "\t\t    \"DO_WHILE\"\n" +
	    "\t\t],\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"NeedBraces\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"max\": 1,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"NestedForDepth\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"max\": 1,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"NestedIfDepth\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"max\": 1,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"NestedTryDepth\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"option\": \"questionMark\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"NullableParameter\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"ternaryOpPolicy\": \"around\",\n" +
	    "\t\t\"unaryOpPolicy\": \"none\",\n" +
	    "\t\t\"boolOpPolicy\": \"around\",\n" +
	    "\t\t\"intervalOpPolicy\": \"none\",\n" +
	    "\t\t\"assignOpPolicy\": \"around\",\n" +
	    "\t\t\"functionArgPolicy\": \"around\",\n" +
	    "\t\t\"bitwiseOpPolicy\": \"around\",\n" +
	    "\t\t\"arithmeticOpPolicy\": \"around\",\n" +
	    "\t\t\"compareOpPolicy\": \"around\",\n" +
	    "\t\t\"arrowPolicy\": \"around\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"OperatorWhitespace\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"tokens\": [\n" +
	    "\t\t    \"=\",\n" +
	    "\t\t    \"+\",\n" +
	    "\t\t    \"-\",\n" +
	    "\t\t    \"*\",\n" +
	    "\t\t    \"/\",\n" +
	    "\t\t    \"%\",\n" +
	    "\t\t    \">\",\n" +
	    "\t\t    \"<\",\n" +
	    "\t\t    \">=\",\n" +
	    "\t\t    \"<=\",\n" +
	    "\t\t    \"==\",\n" +
	    "\t\t    \"!=\",\n" +
	    "\t\t    \"&\",\n" +
	    "\t\t    \"|\",\n" +
	    "\t\t    \"^\",\n" +
	    "\t\t    \"&&\",\n" +
	    "\t\t    \"||\",\n" +
	    "\t\t    \"<<\",\n" +
	    "\t\t    \">>\",\n" +
	    "\t\t    \">>>\",\n" +
	    "\t\t    \"+=\",\n" +
	    "\t\t    \"-=\",\n" +
	    "\t\t    \"*=\",\n" +
	    "\t\t    \"/=\",\n" +
	    "\t\t    \"%=\",\n" +
	    "\t\t    \"<<=\",\n" +
	    "\t\t    \">>=\",\n" +
	    "\t\t    \">>>=\",\n" +
	    "\t\t    \"|=\",\n" +
	    "\t\t    \"&=\",\n" +
	    "\t\t    \"^=\",\n" +
	    "\t\t    \"...\",\n" +
	    "\t\t    \"=>\",\n" +
	    "\t\t    \"++\",\n" +
	    "\t\t    \"--\"\n" +
	    "\t\t],\n" +
	    "\t\t\"option\": \"eol\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"OperatorWrap\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"ignoreExtern\": true,\n" +
	    "\t\t\"format\": \"^(_|[a-z][a-zA-Z0-9]*$)\",\n" +
	    "\t\t\"tokens\": [],\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"ParameterName\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"max\": 7,\n" +
	    "\t\t\"severity\": \"INFO\",\n" +
	    "\t\t\"ignoreOverriddenMethods\": false\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"ParameterNumber\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"enforcePublicPrivate\": false,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"RedundantModifier\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"allowEmptyReturn\": true,\n" +
	    "\t\t\"enforceReturnType\": false,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"Return\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"ignoreFormat\": \"^$\",\n" +
	    "\t\t\"max\": 2,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"ReturnCount\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"tokens\": [\n" +
	    "\t\t    \"CLASS_DEF\",\n" +
	    "\t\t    \"ENUM_DEF\",\n" +
	    "\t\t    \"ABSTRACT_DEF\",\n" +
	    "\t\t    \"TYPEDEF_DEF\",\n" +
	    "\t\t    \"INTERFACE_DEF\",\n" +
	    "\t\t    \"OBJECT_DECL\",\n" +
	    "\t\t    \"FUNCTION\",\n" +
	    "\t\t    \"FOR\",\n" +
	    "\t\t    \"IF\",\n" +
	    "\t\t    \"WHILE\",\n" +
	    "\t\t    \"SWITCH\",\n" +
	    "\t\t    \"TRY\",\n" +
	    "\t\t    \"CATCH\"\n" +
	    "\t\t],\n" +
	    "\t\t\"option\": \"aloneorsingle\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"RightCurly\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"commaPolicy\": \"after\",\n" +
	    "\t\t\"semicolonPolicy\": \"after\",\n" +
	    "\t\t\"dotPolicy\": \"none\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"SeparatorWhitespace\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"tokens\": [\n" +
	    "\t\t    \",\",\n" +
	    "\t\t    \".\"\n" +
	    "\t\t],\n" +
	    "\t\t\"option\": \"eol\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"SeparatorWrap\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"SimplifyBooleanExpression\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"SimplifyBooleanReturn\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"spaceIfCondition\": true,\n" +
	    "\t\t\"spaceAroundBinop\": true,\n" +
	    "\t\t\"spaceForLoop\": true,\n" +
	    "\t\t\"ignoreRangeOperator\": true,\n" +
	    "\t\t\"spaceWhileLoop\": true,\n" +
	    "\t\t\"spaceCatch\": true,\n" +
	    "\t\t\"spaceSwitchCase\": true,\n" +
	    "\t\t\"noSpaceAroundUnop\": true,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"Spacing\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"policy\": \"doubleAndInterpolation\",\n" +
	    "\t\t\"allowException\": true,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"StringLiteral\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"format\": \"^\\\\s*(TODO|FIXME|HACK|XXX|BUG)\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"TODOComment\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"ignorePattern\": \"^$\",\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"TabForAligning\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"Trace\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"TrailingWhitespace\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"ignoreEnumAbstractValues\": true,\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"Type\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"ignoreExtern\": true,\n" +
	    "\t\t\"format\": \"^[A-Z]+[a-zA-Z0-9]*$\",\n" +
	    "\t\t\"tokens\": [],\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"TypeName\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"UnnecessaryConstructor\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"moduleTypeMap\": {},\n" +
	    "\t\t\"ignoreModules\": [],\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"UnusedImport\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"VariableInitialisation\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"tokens\": [\n" +
	    "\t\t    \",\",\n" +
	    "\t\t    \";\"\n" +
	    "\t\t],\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"WhitespaceAfter\"\n" +
	    "\t},\n" +
	    "\t{\n" +
	    "\t    \"props\": {\n" +
	    "\t\t\"tokens\": [\n" +
	    "\t\t    \"=\",\n" +
	    "\t\t    \"+\",\n" +
	    "\t\t    \"-\",\n" +
	    "\t\t    \"*\",\n" +
	    "\t\t    \"/\",\n" +
	    "\t\t    \"%\",\n" +
	    "\t\t    \">\",\n" +
	    "\t\t    \"<\",\n" +
	    "\t\t    \">=\",\n" +
	    "\t\t    \"<=\",\n" +
	    "\t\t    \"==\",\n" +
	    "\t\t    \"!=\",\n" +
	    "\t\t    \"&\",\n" +
	    "\t\t    \"|\",\n" +
	    "\t\t    \"^\",\n" +
	    "\t\t    \"&&\",\n" +
	    "\t\t    \"||\",\n" +
	    "\t\t    \"<<\",\n" +
	    "\t\t    \">>\",\n" +
	    "\t\t    \">>>\",\n" +
	    "\t\t    \"+=\",\n" +
	    "\t\t    \"-=\",\n" +
	    "\t\t    \"*=\",\n" +
	    "\t\t    \"/=\",\n" +
	    "\t\t    \"%=\",\n" +
	    "\t\t    \"<<=\",\n" +
	    "\t\t    \">>=\",\n" +
	    "\t\t    \">>>=\",\n" +
	    "\t\t    \"|=\",\n" +
	    "\t\t    \"&=\",\n" +
	    "\t\t    \"^=\",\n" +
	    "\t\t    \"=>\"\n" +
	    "\t\t],\n" +
	    "\t\t\"severity\": \"INFO\"\n" +
	    "\t    },\n" +
	    "\t    \"type\": \"WhitespaceAround\"\n" +
	    "\t}\n" +
	    "    ]\n" +
	    "}";

    public Config() {
	super("Generate _Default Config");
    }

    public void actionPerformed(AnActionEvent event) {
	Project project = event.getData(PlatformDataKeys.PROJECT);
	try {
	    File dest = new File(event.getProject().getBasePath() + "/checkstyle.json");
	    if (!dest.exists()) {
		FileWriter fileWriter = new FileWriter(event.getProject().getBasePath() + "/checkstyle.json");
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(json);
		bufferedWriter.close();
		Messages.showMessageDialog(project, "Config generated successfully.", "Haxe Checkstyle", Messages.getInformationIcon());
	    } else {
		Messages.showMessageDialog(project, "Config already exists.", "Haxe Checkstyle", Messages.getInformationIcon());
	    }
	} catch (FileNotFoundException ex) {
	} catch (IOException ex) {
	}
    }
}
