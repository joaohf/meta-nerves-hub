SUMMARY = "Agent for NervesHub"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

SRC_URI = "git://github.com/joaohf/nerves_hub_agent;protocol=https;branch=main \
           file://nerves_hub_agent.service \
           file://nerves_hub_agent.init \
           "

PV = "0.1.0"
SRCREV = "836747a43a02be5b5872e51a06abd566787b8edf"

S = "${WORKDIR}/git"

MIX_ENV = "yocto"

DEPENDS = "libmnl"
RDEPENDS:${PN} += "fwup busybox boardid libubootenv-bin ca-certificates"

# TODO: what happen if busybox is not provided ?
RDEPENDS:${PN} += "busybox"

inherit mix systemd update-rc.d

ERL_INTERFACE_VERSION = "`pkg-config --modversion erl_ei`"
export ERL_EI_LIBDIR = "${STAGING_LIBDIR}/erlang/lib/erl_interface-${ERL_INTERFACE_VERSION}/lib/"
export ERL_EI_INCLUDE_DIR = "${STAGING_LIBDIR}/erlang/lib/erl_interface-${ERL_INTERFACE_VERSION}/include"

INSANE_SKIP:${PN} += "buildpaths"
INSANE_SKIP:${PN}-dev-tools += "buildpaths"
INSANE_SKIP:${PN}-erts += "buildpaths"

INITSCRIPT_NAME = "nerves_hub_agent"
INITSCRIPT_PARAMS = "start 99 5 2 3 . stop 20 0 1 6 ."

SYSTEMD_SERVICE:${PN} = "nerves_hub_agent.service"

do_install:append() {
    # Install systemd unit files
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${UNPACKDIR}/nerves_hub_agent.service ${D}${systemd_unitdir}/system
    fi

    # Install init.d
    if ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','true','false',d)}; then
        install -Dm 0755 ${UNPACKDIR}/nerves_hub_agent.init ${D}/${sysconfdir}/init.d/nerves_hub_agent
    fi
}