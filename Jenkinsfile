pipeline {
  agent any
  stages {
    stage('error') {
      steps {
        sh 'mvn clean package'
      }
    }

    stage('build') {
      steps {
        sh 'cp /root/.jenkins/workspace/GHISMED-V01_master/target/IHisMan-1.0.war /opt/wildfly/standalone/deployments'
      }
    }

  }
}