#!/usr/bin/env python

import jwms_lib

from jwms_lib import jwms_get_jenkins_crumb, jwms_send

# change to your settings
jwms_lib.JWMS_JENKINS_CRUMB=''
jwms_lib.JWMS_JENKINS_USERNAME=''
jwms_lib.JWMS_JENKINS_API_TOKEN=''
jwms_lib.JWMS_JENKINS_HOSTNAME=''


if __name__ == "__main__":

    res = jwms_send('testing from py lib\n', 0, 200000, 'my.test-server.com', 'automatic maintenance 28-10-2016')
    print(res)
    print(jwms_get_jenkins_crumb())
