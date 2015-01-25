module.exports = function(grunt) {
    var dependencies = Object.keys(
        grunt.file
            .readJSON('bower.json').dependencies);


    function constructDependencies() {
        var deps = dynamicDependencies();

    }

    function staticDependencies() {


    }

    function dynamicDependencies() {
        var result = {};
        for(var i = 0; i < dependencies.length; ++i) {
            var name = dependencies[i];
            result[name + '/' + name + '.js'] = name + '/' + name + '.js';
        }
        return result;

    }


    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        karma: {
            unit: {
                configFile: 'karma.conf.js'
            }
        },

        connect: {
            server: {
                options: {
                    port:8081,
                    base: './src/main',
                    keepalive: true
                }
            }
        },

        copy : {
            styles: {
                cwd: 'bower_components',
                src: 'jquery-ui/themes/base/**/**.*',
                dest: 'src/main/resources/styles/sass/libs/',
                expand: true,
                rename: function(i, o) {
                    return i + o.replace('.css', '.scss')
                }
            },
            zurb: {
                cwd: 'bower_components/foundation/scss',
                src: './**/*.scss',
                dest: 'src/main/resources/styles/sass/libs/foundation',
                expand: true
            },
            images : {
                cwd: 'bower_components/jquery-ui/themes/base/images',
                src: './*.*',
                dest: 'src/main/resources/styles/css/images',
                expand: true
            },
            foundation : {
                cwd: 'bower_components/foundation/js/',
                src: './**/*.js',
                dest: 'src/main/js/lib/zurb',
                expand:true
            }


        },

        bowercopy: {
            libs: {
                options: {
                    destPrefix: 'src/main/js/lib'
                },
                files: {
                    'datatables/datatables.js': 'datatables/media/js',
                    'dropzone/dropzone.js' : 'dropzone/downloads/dropzone-amd-module.js',
                    'angular/angular.js' : 'angular/angular.js',
                    'require/require.js' : 'requirejs/require.js',
                    'jquery/jquery.js' : 'jquery/dist/jquery.js',
                    'ready/ready.js' : 'domready/ready.js',
                    'jquery/ui' : 'jquery-ui/ui/*.js',
                    'interact/interact.js' : 'interact/interact.js'
                }
            },

            styles : {
                options: {
                    destPrefix: 'src/main/resources/styles/css/libs'
                },
                files : {
                    'datatables/datatables.css' : 'datatables/media/css/jquery.dataTables.css'
                }
            }
       
        },

        sass: {
            dist: {
                options: {
                    style: 'expanded'
                },
                files: {
                    'src/main/resources/styles/css/main.css' :
                        'src/main/resources/styles/sass/main.scss'
                }
            }
        },
        watch: {
            files: ['src/main/resources/styles/sass/**/*'],
            tasks: ['sass']
        }
    });

    grunt.loadNpmTasks('grunt-bowercopy');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-sass');
    grunt.loadNpmTasks('grunt-contrib-connect');
    grunt.loadNpmTasks('grunt-karma');

};
