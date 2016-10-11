module.exports = function (grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON("package.json"),

        clean : {
            dir: {
                files: [{src:"haxe-checkstyle/"}]
            }
        },

        shell: {
            dir: {
                command: "mkdir haxe-checkstyle || true && mkdir haxe-checkstyle/classes || true && mkdir haxe-checkstyle/META-INF || true"
            },
            files: {
                command: "cp out/production/haxe-checkstyle-intellij-plugin/META-INF/plugin.xml haxe-checkstyle/META-INF/" +
                " && cp -r out/production/haxe-checkstyle-intellij-plugin/haxe/ haxe-checkstyle/classes/haxe" +
                " && cp -r out/production/haxe-checkstyle-intellij-plugin/checkstyle.json haxe-checkstyle/classes/" +
                " && cp -r out/production/haxe-checkstyle-intellij-plugin/run haxe-checkstyle/classes/"
            }
        },

        zip: {
            "haxe-checkstyle.zip": ["haxe-checkstyle/**"]
        }
    });

    grunt.loadNpmTasks("grunt-contrib-clean");
    grunt.loadNpmTasks("grunt-zip");
    grunt.loadNpmTasks("grunt-shell");
    grunt.registerTask("default", ["clean", "shell", "zip"]);
};