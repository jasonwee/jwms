Name:           java-jwms-lib
Version:        0.1.0
Release:        1%{?dist}
Summary:        Jason Wee Monitoring System java implementation

License:        Apache License, Version 2.0
URL:            https://github.com/jasonwee/jwms
Source0:        jwms-java-lib-%{version}.tar.gz

BuildArch:      noarch
BuildRequires:  java >= 1.8
Requires:       java>= 1.8

%description
jwms or Jason Wee Monitor System is a library that bridge between the monitored
node and the monitor node.

%prep

%setup -q

%build
gradle jar

%install
install -p -m 644 jwms-java-lib-0.1.0.jar %{buildroot}%{_javadir}/jwms-java-lib-0.1.0.jar

%jpackage_script co.weetech.jwms.Application "" "" %{name} jwms-application true

%files
%{_javadir}/jwms-java-lib-0.1.0.jar
%{_bindir}/jwms-application

%changelog
* Tue Nov 15 2016 Jason Wee <peichieh@gmail.com> - 0.1.0-0
- This is first version
