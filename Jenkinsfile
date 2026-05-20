pipeline {
    agent any
    
    tools {
        maven 'Maven-3.8.7'
        jdk 'JDK-17'
    }
    
    stages {
        stage('1. Clonar Código') {
            steps {
                git branch: 'main', 
                    url: 'https://github.com/beatrizfouve1/BibliotecaProyectoJenkins.git'
                echo '✅ Código descargado correctamente'
            }
        }
        
        stage('2. Compilar Código') {
            steps {
                sh 'mvn clean compile'
                echo '✅ Compilación exitosa'
            }
        }
        
        stage('3. Ejecutar Pruebas Unitarias') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    echo '📊 Resultados de pruebas publicados'
                }
            }
        }
        
        stage('4. Empaquetar Aplicación') {
            steps {
                sh 'mvn package'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                    echo '📦 Artefacto archivado en Jenkins'
                }
            }
        }
        
        stage('5. Desplegar Artefacto') {
            steps {
                sh '''
                    mkdir -p /tmp/artefactos-biblioteca
                    cp target/*.jar /tmp/artefactos-biblioteca/
                    echo "========================================="
                    echo "📚 ARTeFACTO DESPLEGADO"
                    echo "========================================="
                    echo "Ubicación: /tmp/artefactos-biblioteca/"
                    ls -la /tmp/artefactos-biblioteca/
                    echo "========================================="
                '''
            }
        }
    }
    
    post {
        success {
            echo '🎉 PIPELINE EXITOSO - La Biblioteca está lista!'
        }
        failure {
            echo '💥 PIPELINE FALLÓ - Revisa los logs'
        }
    }
}
