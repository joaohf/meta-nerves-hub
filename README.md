# OpenEmbedded/Yocto Project layer for NervesHub support

This layer provides support for [NervesHub](https://github.com/nerves-hub) based applications.

For use with [OpenEmbedded](http://www.openembedded.org/wiki/Main_Page) and/or
the [Yocto Project](https://www.yoctoproject.org/) build system.

## Documentation

Right now, there is no official documentation about how to use this layer. However,
the blog post [fwup for A/B image upgrades on QEMU machines with Nerves Cloud, part III](https://meta-erlang.github.io//blog/2025/01/26/index) gives a nice tutorial.

## Dependencies

This layer depends on:

  URI: git://git.openembedded.org/openembedded-core
  layers: meta
  branch: master

  URI: https://github.com/meta-erlang/meta-erlang
  layers: meta-erlang
  branch: master

## Contributing

You can send patches using the GitHub pull request process.

## Maintainers

* João Henrique Ferreira de Freitas `<joaohf@gmail.com>`
