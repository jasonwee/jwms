#!/usr/bin/env python

#
#   Copyright 2016 Jason Wee
#
#   Licensed under the Apache License, Version 2.0 (the "License");
#   you may not use this file except in compliance with the License.
#   You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
#   Unless required by applicable law or agreed to in writing, software
#   distributed under the License is distributed on an "AS IS" BASIS,
#   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#   See the License for the specific language governing permissions and
#   limitations under the License.

from requests.auth import HTTPBasicAuth

import requests
import binascii

JWMS_JENKINS_CRUMB=''
JWMS_JENKINS_USERNAME=''
JWMS_JENKINS_API_TOKEN=''
JWMS_JENKINS_HOSTNAME=''

def jwms_str_to_hex(string):
    return binascii.hexlify(string)

def jwms_get_jenkins_crumb():
    url = '{0}/crumbIssuer/api/xml?xpath=concat(//crumbRequestField,%22:%22,//crumb)'.format(JWMS_JENKINS_HOSTNAME)
    auth = HTTPBasicAuth(JWMS_JENKINS_USERNAME, JWMS_JENKINS_API_TOKEN)
    crumb = requests.get(url, auth=auth)
    return crumb.text

def jwms_send(strings, result, duration, display_name, description, debug=False):
     if debug:
         print(strings)
         print(result)
         print(duration)
         print(display_name)
         print(description)

     hex_string = jwms_str_to_hex(strings)
     
     headers = {'.crumb': JWMS_JENKINS_CRUMB, 'user-agent': 'jwms-lib'}
     auth = HTTPBasicAuth(JWMS_JENKINS_USERNAME, JWMS_JENKINS_API_TOKEN)
     data = '<run><log encoding="hexBinary">{}</log><result>{}</result><duration>{}</duration><displayName>{}</displayName><description>{}</description></run>'.format(hex_string, result, duration, display_name, description)
     url = '{0}/job/BigBrother/postBuildResult'.format(JWMS_JENKINS_HOSTNAME)
     r = requests.post(url, headers=headers, auth=auth, data=data)

     if debug:    
         print(vars(r))

     if r.status_code == requests.codes.ok:
         return True, None
     else:
         return False, vars(r)
