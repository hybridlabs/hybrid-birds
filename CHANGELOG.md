# Changelog for [Hybrid Birds](https://github.com/hybridlabs/hybrid-birds)

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.3.4] 2026-11-24

- Birds now spawn during world generation and stay in the world, like vanilla animals.
- Leashed birds, tamed birds, bred birds and creature-netted birds no longer disappear after you leave and return.
- Fixed a startup crash.
- Fixed a crash involving the puffin.
- Fixed baby pelicans.

### Developer notes

- Birds are registered in the CREATURE spawn group, so they spawn during world generation like vanilla animals.
- Leashed, tamed, bred and creature-netted birds are flagged persistent so the distance-despawn check does not remove them.

## [1.0.0] 2025-08-22

Initial public release.
