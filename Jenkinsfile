pipeline {
    agent any
    
    tools {
        maven 'Maven-3.8.7'
        jdk 'JDK-17'
    }
    
    stages {
        stage('1. Clonar Código') {
            steps {
                git branch: 'master', 
                    url: 'https://github.com/beatrizfouve1/BibliotecaProyectoJenkins.git'
                echo '✅ Código descargado correctamente'
            }
        }
        
        stage('2. Compilar Código') {
            steps {
                sh 'mvn clean compile'
            }
            post {
                success {
                    echo '✅ Compilación exitosa'
                }
                failure {
                    echo '❌ Error en la compilación'
                }
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
        
        stage('4. Análisis SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
                echo '🔍 Análisis de calidad enviado a SonarQube'
            }
        }
        
        stage('5. Esperar Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
                echo '✅ Quality Gate aprobado'
            }
        }
        
        stage('6. Empaquetar Aplicación') {
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
        
        stage('7. Desplegar Artefacto') {
            steps {
                sh '''
                    # Crear directorio para artefactos
                    mkdir -p /tmp/artefactos-biblioteca
                    
                    # Copiar el JAR generado
                    cp target/*.jar /tmp/artefactos-biblioteca/
                    
                    # Mostrar información del despliegue
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
