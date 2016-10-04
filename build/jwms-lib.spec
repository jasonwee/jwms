Summary: Jason Wee Monitoring System
Name: jwms-lib
Version: 0.2.1
Release: 0
URL: https://github.com/jasonwee/jwms
License: Apache-2.0
Group: Development/Libraries
BuildRoot: %{_tmppath}/%{name}-root
Requires: bash
Requires: util-linux-ng
Requires: coreutils
Source0: jwms-lib-%{version}.tar.gz
BuildArch: noarch

%description
jwms or Jason Wee Monitor System is a library that bridge between the monitored
node and the monitor node.

%prep
%setup

%build

%install
rm -rf ${RPM_BUILD_ROOT}
mkdir -p ${RPM_BUILD_ROOT}/usr/share/jwms-lib/sh/
install -m 644 src/sh/jwms-lib.sh ${RPM_BUILD_ROOT}/usr/share/jwms-lib/sh/
install -m 644 src/sh/jwms-util-datetime.sh ${RPM_BUILD_ROOT}/usr/share/jwms-lib/sh/

%clean
rm -rf ${RPM_BUILD_ROOT}

%files
%defattr(-,root,root)
%attr(644,root,root) /usr/share/jwms-lib/sh/jwms-lib.sh
%attr(644,root,root) /usr/share/jwms-lib/sh/jwms-util-datetime.sh

%changelog
* Tue Oct 04 2016 Jason Wee <peichieh@gmail.com>
- fix jwms_send function call
- support for backslash character when convert to hex

* Tue Oct 04 2016 Jason Wee <peichieh@gmail.com>
- added unit test jwms-util-datetime.sh
- added curl parameter to jwms-lib.sh
- added function jwms_str_to_hex to jwms-lib.sh
- now automatically hex encode to first parameter of function jwms_send

* Sun Oct 02 2016 Jason Wee <peichieh@gmail.com>
- Initial
