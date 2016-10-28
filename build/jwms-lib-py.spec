Name:           python-jwms-lib
Version:        0.1.0
Release:        1%{?dist}
Summary:        Jason Wee Monitoring System python implementation

License:        Apache-2.0
#URL:           https://pypi.python.org/pypi/jwms-lib
URL:            https://github.com/jasonwee/jwms
Source0:        jwms-lib-%{version}.tar.gz

BuildArch:      noarch
BuildRequires:  python2-devel python3-devel
Requires:       python-requests

%description
jwms or Jason Wee Monitor System is a library that bridge between the monitored
node and the monitor node.

%package -n python2-jwms-lib
Summary:        Jason Wee Monitoring System python implementation
%{?python_provide:%python_provide python2-jwms-lib}

%description -n python2-jwms-lib
An python module which provides a convenient example.


%package -n python3-jwms-lib
Summary:        Jason Wee Monitoring System python implementation
%{?python_provide:%python_provide python3-jwms-lib}

%description -n python3-jwms-lib
An python module which provides a convenient example.


%prep
%autosetup -n jwms-lib-%{version}

%build
%py2_build
%py3_build

%install
# Must do the python2 install first because the scripts in /usr/bin are
# overwritten with every setup.py install, and in general we want the
# python3 version to be the default.
%py2_install
%py3_install

%check
%{__python2} setup.py test
%{__python3} setup.py test

# Note that there is no %%files section for the unversioned python module if we
# are building for several python runtimes
%files -n python2-jwms-lib
%license COPYING
%doc README.rst
%{python2_sitelib}/*
%{_bindir}/sample-exec-2.7

%files -n python3-jwms-lib
%license COPYING
%doc README.rst
%{python3_sitelib}/*
%{_bindir}/sample-exec
%{_bindir}/sample-exec-3.4

%changelog
