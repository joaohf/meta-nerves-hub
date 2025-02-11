SUMMARY = "Connect devices to NervesHub via a Phoenix channel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/nerves-hub/nerves_hub_link;protocol=https;branch=main \
           file://0001-Use-MIX_TARGET_INCLUDE_ERTS-for-include-ERTS-release.patch \
           file://0001-Use-UbootEnv-as-kv_backend.patch \
           "

PV = "2.5.2"
SRCREV = "3a993974a1187d1f8eebb08b421a20efa89b01c3"

S = "${WORKDIR}/git"

MIX_ENV = "dev"

DEPENDS = "libmnl"
RDEPENDS:${PN} += "fwup busybox boardid"

export ERL_CFLAGS = "-I${STAGING_LIBDIR}/erlang/lib/${@get_erlang_application(d, "erl_interface")}/include \
                     -I${STAGING_LIBDIR}/erlang/${@get_erlang_application(d, "erts")}/include"

export ERL_EI_LIBDIR = "${STAGING_LIBDIR}/erlang/lib/${@get_erlang_application(d, "erl_interface")}/lib"

inherit mix erlang-version

INSANE_SKIP:${PN} += "buildpaths"
INSANE_SKIP:${PN}-dev-tools += "buildpaths"
INSANE_SKIP:${PN}-erts += "buildpaths"
