Name:           jwms-java-lib
Version:        0.1.0
Release:        1%{?dist}
Summary:        Jason Wee Monitoring System java implementation

License:        Apache License, Version 2.0
URL:            https://github.com/jasonwee/jwms
Source0:        jwms-java-lib-%{version}.tar.gz

BuildArch:      noarch
BuildRequires:  java >= 1.8
Requires:       java >= 1.8
Requires:       slf4j
Requires:       log4j

%description
jwms or Jason Wee Monitor System is a library that bridge between the monitored
node and the monitor node.

%prep

%setup -q

%build
gradle/gradle-3.1/bin/gradle jar

%install
mkdir -p %{buildroot}%{_javadir}
mkdir -p %{buildroot}%{_bindir}

install -p -m 644 build/libs/jwms-java-lib-0.1.0.jar %{buildroot}%{_javadir}/jwms-java-lib-0.1.0.jar

%jpackage_script co.weetech.jwms.Application "" "" jwms-java-lib-0.1.0.jar:slf4j/api.jar:slf4j/log4j-over-slf4j.jar:log4j.jar:slf4j/simple.jar jwms-application true

%files
%{_javadir}/jwms-java-lib-0.1.0.jar
%{_bindir}/jwms-application

%changelog
* Tue Nov 15 2016 Jason Wee <peichieh@gmail.com> - 0.1.0-0
- This is first version
