SUMMARY = "Print out a platform-specific board serial number"
DESCRIPTION = "This program looks up a device-specific serial number and prints it. \
The original use was to provide some non-changing and unique material for dynamically \
creating device names on a local LAN."
LICENSE = "Apache-2.0 & CC0-1.0 & CC-BY-4.0"
LIC_FILES_CHKSUM = "file://LICENSES/Apache-2.0.txt;md5=c846ebb396f8b174b10ded4771514fcc \
	                file://LICENSES/CC0-1.0.txt;md5=65d3616852dbf7b1a6d4b53b00626032 \
					file://LICENSES/CC-BY-4.0.txt;md5=9b33bbd06fb58995fb0e299cd38d1838"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

SRC_URI = "git://github.com/nerves-project/boardid/;protocol=https;branch=main \
           file://boardid.config \
           "

PV = "1.0+git"
SRCREV = "060c2f856c37899961d12deb3f9f304cf1cbf3a5"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
        install -d ${D}${bindir}
        install -m 0755 ${S}/boardid ${D}${bindir}

        install -d ${D}${sysconfdir}
        install -m 0644 ${UNPACKDIR}/boardid.config ${D}${sysconfdir}
}

