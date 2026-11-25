# Changelog for [Hybrid Birds](https://github.com/hybridlabs/hybrid-birds)

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

- Fixed a client crash when a baby puffin is rendered. Baby albatrosses, pelicans and seagulls now use their adult model and texture instead of a missing one.

## [1.3.4] 2026-11-24

- Now available for Minecraft 26.3.
- Birds now spawn during world generation and stay in the world, like vanilla animals.
- Leashed, tamed, bred and creature-netted birds no longer disappear after you leave and return.

### Developer notes

- Birds are registered in the CREATURE spawn group, so they spawn during world generation like vanilla animals.
- Leashed, tamed, bred and creature-netted birds are flagged persistent so the distance-despawn check does not remove them.

## [1.3.3] 2026-09-16

- Fix baby-ahh pelican

## [1.3.2] 2026-09-15

- Fixed a crash where the Puffin model couldn't load

## [1.3.1] 2026-09-13

- Fixed a mixin issue
