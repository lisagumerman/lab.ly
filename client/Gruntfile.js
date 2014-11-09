module.exports = function(grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

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


        watch : {
            files :'scss/*',
            tasks: ['sass']
        }

    });

    grunt.loadNpmTasks("grunt-bowercopy");
    grunt.loadNpmTasks("grunt-contrib-connect");
    grunt.loadNpmTasks("grunt-contrib-uglify");
    grunt.loadNpmTasks("grunt-contrib-copy");
    grunt.loadNpmTasks("grunt-contrib-compress");
    grunt.loadNpmTasks('grunt-bower-task');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks("grunt-contrib-sass");
};
