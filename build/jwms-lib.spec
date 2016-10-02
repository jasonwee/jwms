Summary: Jason Wee Monitoring System
Name: jwms-lib
Version: 0.1.0
Release: 0
URL: https://github.com/jasonwee/jwms
License: Apache-2.0
Group: Development/Libraries
BuildRoot: %{_tmppath}/%{name}-root
Requires: bash
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

%clean
rm -rf ${RPM_BUILD_ROOT}

%files
%defattr(-,root,root)
%attr(644,root,root) /usr/share/jwms-lib/sh/jwms-lib.sh

%changelog
* Sun Oct 02 2016 Jason Wee <peichieh@gmail.com>
- Initial
