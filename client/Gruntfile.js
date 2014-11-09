module.exports = function(grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        bowercopy: {
            libs: {
                options: {
                    destPrefix: 'js/libs'
                },
                files: {
                    'angular.js': 'angular/angular.js'
                },
            },

        },



        connect: {
            server: {
                options: {
                    port: 8081,
                    base: '.',
                    keepalive:true
                }
            }
        },

        sass: {                              
            dist: {                            
                options: {                      
                    style: 'expanded'
                },
                files: {                         
                    'css/main.css': 'scss/main.scss',     
                    }
                }
        },

        jade: {
            compile: {
                options: {
                    data: {
                        debug: false
                    }
            },
            files: {
                    "html/application.html": ["jade/**/*.jade"]
                }
            }
        },


        watch : {
            files :'scss/*',
            tasks: ['sass', 'jade']
        } 
    });

    grunt.loadNpmTasks("grunt-bowercopy");
    grunt.loadNpmTasks("grunt-contrib-connect");
    grunt.loadNpmTasks("grunt-contrib-uglify");
    grunt.loadNpmTasks("grunt-contrib-copy");
    grunt.loadNpmTasks("grunt-contrib-compress");
    grunt.loadNpmTasks('grunt-bower-task');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-jade');
    grunt.loadNpmTasks("grunt-contrib-sass");


    grunt.registerTask('default', ['jade', 'sass', 'bowercopy']);
};
