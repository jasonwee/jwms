#!/usr/bin/env bash

JWMS_JENKINS_USERNAME=""
JWMS_JENKINS_API_TOKEN=""
JWMS_JENKINS_HOSTNAME=""
JWMS_JENKINS_JOB_NAME=""
JWMS_JENKINS_CRUMB=""

CURL="`which curl`"

function sendToJenkins {
  if [ $# -ne 5 ]; then
     echo "ERROR: five parameters required. hex binary, result, duration, display name and description."
     return 1
  fi

  l_hex_bin=$1
  l_result=$2
  l_duration=$3
  l_display_name=$4
  l_description=$5

  checkRequireFields "$JWMS_JENKINS_USERNAME" "jenkins username" && return 1
  checkRequireFields "$JWMS_JENKINS_API_TOKEN" "jenkins api token" && return 1
  checkRequireFields "$JWMS_JENKINS_HOSTNAME" "jenkins hostname" && return 1
  checkRequireFields "$JWMS_JENKINS_JOB_NAME" "jenkins job name" && return 1
  checkRequireFields "$JWMS_JENKINS_CRUMB" "jenkins crumb" && return 1

  checkRequireFields "$l_hex_bin" "hex binary" && return 1
  checkRequireFields "$l_result" "result" && return 1
  checkRequireFields "$l_duration" "duration" && return 1
  checkRequireFields "$l_display_name" "display name" && return 1
  checkRequireFields "$l_description" "description" && return 1

  $CURL -XPOST \
       -H".crumb:$JWMS_JENKINS_CRUMB" \
       -u "$JWMS_JENKINS_USERNAME:$JWMS_JENKINS_API_TOKEN" \
       -d "<run><log encoding=\"hexBinary\">$l_hex_bin</log><result>$l_result</result><duration>$l_duration</duration><displayName>$l_display_name</displayName><description>$l_description</description></run>" \
       "$JWMS_JENKINS_HOSTNAME/job/$JWMS_JENKINS_JOB_NAME/postBuildResult"
}

function checkRequireFields {
   l_require=$1
   l_field=$2

   if [ -z "$l_require" ]; then
      echo "ERROR: $l_field parameter required"
      return 0
   fi
   return 1
}
