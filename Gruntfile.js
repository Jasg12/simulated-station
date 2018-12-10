module.exports = function(grunt) {
    grunt.loadNpmTasks('grunt-shell');
    grunt.loadNpmTasks('grunt-ssh');
    grunt.loadNpmTasks('grunt-replace');
    grunt.loadNpmTasks('grunt-gitinfo');

    var home = grunt.option("simulated-station_home") || __dirname ;
    grunt.initConfig({
        gitinfo: {
            commands: {
                'my.custom.command': ['describe','--always']
            }
        },
        shell: {
            build: {
                command: [
                    'echo ' + home,
                 //   'echo <%= secret.password %> | sudo -S whoami',
                    'mkdir -p ' + home + 'deploy',
                    'rm -rf ' + home + 'deploy/*.jar',
                //    'cd ..',
                    'mvn clean package',
                    'pwd',
                    'cp -r target/*.jar deploy/simulated-station.jar'
                ].join('&&')
            },
            build_docker_image: {
                command: [
                    //    'echo <%= secret.password %> | sudo -S whoami',
                    'docker login -u amatsarskisjsu -p 123ZXCzxc',
                    'cd ' + home,
                    'docker build --tag="amatsarskisjsu/simulated-station:<%= gitinfo.my.custom.command %>" .'
                ].join('&&'),
                options: {
                    execOptions: {
                        maxBuffer: Infinity
                    }
                }
            },
            push_docker_image: {
                command: [
                    //    'echo <%= secret.password %> | sudo -S whoami',
                    //    'echo "gshivani08"|docker login',
                    'docker login -u amatsarskisjsu -p 123ZXCzxc',
                    'docker push amatsarskisjsu/simulated-station:<%= gitinfo.my.custom.command %>'
                ].join('&&')
            }
        },
        // Pass file name as argument to call this task e.g grunt --config qa shell
        //  secret: grunt.file.readJSON(grunt.option("config")+'.json'),
        sshexec: {
            //Placeholder for future
        }
    });
    grunt.registerTask('buildAll', ['gitinfo','shell:build', 'shell:build_docker_image','shell:push_docker_image']);
};