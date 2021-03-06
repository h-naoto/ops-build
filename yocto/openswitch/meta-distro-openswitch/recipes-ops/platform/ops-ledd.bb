SUMMARY = "OpenSwitch LED Daemon"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "ops-config-yaml ops-ovsdb"

SRC_URI = "git://git.openswitch.net/openswitch/ops-ledd;protocol=http \
           file://ops-ledd.service \
"

SRCREV = "d70ad5216113926da24c8a9c7fe44897a8693ca2"

# When using AUTOREV, we need to force the package version to the revision of git
# in order to avoid stale shared states.
PV = "git${SRCPV}"

S = "${WORKDIR}/git"

do_install_append() {
     install -d ${D}${systemd_unitdir}/system
     install -m 0644 ${WORKDIR}/ops-ledd.service ${D}${systemd_unitdir}/system/
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "ops-ledd.service"

inherit openswitch cmake systemd
