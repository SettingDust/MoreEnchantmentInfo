# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.4.0] - 2026-04-29
### :sparkles: New Features
- [`45bdc1e`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/45bdc1e26e6bdec59ef642ff282deee0eae31c92) - add getIdentifier override in v26 EnchantmentRecipeCategory *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`f9312a8`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/f9312a868902da525f6f8ecc3c552234e4463008) - add separate loader containers for Forge and NeoForge *(commit by [@SettingDust](https://github.com/SettingDust))*

### :bug: Bug Fixes
- [`032838e`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/032838ee64a119c23fe21a6b67fd6c6b263f45e3) - white line at tradable right *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`36bc510`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/36bc510e83a023db0d592fb05e01d4b6601eee4f) - add requireNotNull(MOD_BUS) guard to NeoForge v21/v26 entrypoints *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`dd9e372`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/dd9e37219e5f94baac2fbf50f65113ec4807b767) - update ForgePluginFinderMixin to use @Inject+@Local pattern *(commit by [@SettingDust](https://github.com/SettingDust))*

### :recycle: Refactors
- [`a30874c`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/a30874c2b01b90615763ff9632870c2be03f352d) - update from template *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`63bf4c8`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/63bf4c80c5f27f0be728c827e7c5d0e24cac4932) - bump copier template *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`a9b3293`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/a9b329355c3fab6f8c04f089fd378684c07f9c35) - **build**: split forge and neoforge runtime targets *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`c50f35c`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/c50f35ca6179008d674295eb2ab67f4967af33be) - **common**: split version adapters and jei abstractions *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`ea8db70`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/ea8db701ab2f2cf0e884467bead8a90fb15ecf3d) - **loader**: wire per-version sprite entrypoints *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`e06f081`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/e06f0813ab8a8872a8eb20e0f5ad88e75c69859d) - **jei**: migrate enchantment recipes/ingredients to Holder and simplify factory API *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`c75ab44`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/c75ab44c2300945a39c194e369eee78db9c96069) - **neoforge**: update entrypoint implementations and enhance service loading *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`3c4b27a`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/3c4b27a19e71bb08e76cbd23afcaf5b15795116a) - implement version-specific adaptations and enhance identifier handling *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`dc73c82`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/dc73c8228a539a6cb591c42a5fd12af9dda70b3f) - **jei**: remove expect/actual IDrawable/IDrawableStatic/GuiGraphics, refactor DrawableSprite to data helper *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`b302dad`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/b302dad3b03a8bc14f6d44ca674aa2ad8bda67ab) - **jei/v20**: replace DrawableSpriteAdapter service loader with DrawableSpriteDrawable *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`e9d44b6`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/e9d44b6ed2bcec1214333d34e54afe0efe0fddc8) - **jei/v21**: replace DrawableSpriteAdapter service loader with DrawableSpriteDrawable *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`b9777e3`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/b9777e344dcd0548370a31eca5c49307b510f136) - **jei/v26**: replace DrawableSpriteAdapter service loader with DrawableSpriteDrawable *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`2f5a27d`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/2f5a27de1e83d4e1b29ec20f5955d1ddec9ad5e5) - **jei/common**: remove shared EnchantmentJeiFactory SPI and adapter *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`3cc354a`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/3cc354a969da3c157e3e74ec872937288afb56fd) - **jei/v20**: inline plugin logic and add versioned recipe viewer adapter *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`e4d7bd2`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/e4d7bd24d812128da81d3a1f3dda09c37a90f1bf) - **jei/v21**: remove factory indirection and switch to versioned identifiers *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`f5fa784`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/f5fa78476ce284649ec4e62560ae8e67f98489bc) - **jei/v26**: remove factory indirection and use version-scoped identifier adapters *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`beb5c47`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/beb5c47a6060338c65b261c1fd901d37d07954be) - **fabric**: use version-scoped toNativeIdentifier imports *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`4944516`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/4944516a57d3a399174a546083f5ec11a3f12b58) - rename RegistryAdapter.registryOrThrow to getRegistry *(commit by [@SettingDust](https://github.com/SettingDust))*

### :wrench: Chores
- [`1f04860`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/1f04860940e2adc476662ab297fe95ddb0cf46b3) - **gradle**: update wrapper and 26.1.2 catalog mapping *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`481f601`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/481f6016f18ed4a1bd9783f9851ad62a67f8b32c) - **mixins**: remove explicit refmap entries from shared mixin configs *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`4cb8193`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/4cb819327bd9b9fc02ee67dcea79c5d351ef93c9) - **idea**: switch project JDK to jbr-25 *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`1b59f2c`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/1b59f2c5ef4cd15d73a0bcf56e9eec0d74218a1d) - bump dependencies *(commit by [@SettingDust](https://github.com/SettingDust))*


## [0.3.0] - 2025-09-14
### :sparkles: New Features
- [`ba814af`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/ba814af8c575c2d1cfb6ce9c57a2d5618da11ed8) - support 1.21.1 fabric *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`63d977b`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/63d977b56dbec2f47cfe45accbb9aca0b45c38ac) - support 1.20.1 forge *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`a4b13af`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/a4b13aff9e7dd1a0dbd8412882013241f6ccd81a) - support 1.21.1 neoforge *(commit by [@SettingDust](https://github.com/SettingDust))*

### :recycle: Refactors
- [`c4923c6`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/c4923c6746f34eeba67f49efa4c7ff89a8e5e077) - readd 1.20.1 fabric support *(commit by [@SettingDust](https://github.com/SettingDust))*


## [0.2.0] - 2025-01-23
### :bug: Bug Fixes
- [`9262b96`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/9262b96e69cbbb89930e2f4bb75242582de3fe14) - internal function can't compile *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`d1f01ec`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/d1f01ecac6cb733511f8571f4eca4475831f48db) - missing remap on overwrite *(commit by [@SettingDust](https://github.com/SettingDust))*

### :recycle: Refactors
- [`2ef0b62`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/2ef0b62746de3fe81c1055d81ef9f83c2a407b0f) - use ingredient & add api to query an enchantment *(commit by [@SettingDust](https://github.com/SettingDust))*


## [0.1.1] - 2025-01-01
### :bug: Bug Fixes
- [`11e5485`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/11e5485894381856342740ae9b301152c51b1b38) - needn't fabric mixin to work *(commit by [@SettingDust](https://github.com/SettingDust))*


## [0.1.0] - 2024-12-27
### :sparkles: New Features
- [`b5e765f`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/b5e765fa96eb395551487d6505eb5b4cd47c43cd) - usable on fabric *(commit by [@SettingDust](https://github.com/SettingDust))*
- [`17c4c06`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/17c4c067ff64d5b22d12d870df949a0227a31757) - working in prod forge + fabric *(commit by [@SettingDust](https://github.com/SettingDust))*

### :wrench: Chores
- [`193ecf7`](https://github.com/SettingDust/MoreEnchantmentInfo/commit/193ecf784ff4fb3b3fc62e1f75533b4f7c739b9b) - add icon *(commit by [@SettingDust](https://github.com/SettingDust))*

[0.1.0]: https://github.com/SettingDust/MoreEnchantmentInfo/compare/0.0.0...0.1.0
[0.1.1]: https://github.com/SettingDust/MoreEnchantmentInfo/compare/0.1.0...0.1.1
[0.2.0]: https://github.com/SettingDust/MoreEnchantmentInfo/compare/0.1.1...0.2.0
[0.3.0]: https://github.com/SettingDust/MoreEnchantmentInfo/compare/0.2.0...0.3.0
[0.4.0]: https://github.com/SettingDust/MoreEnchantmentInfo/compare/0.3.0...0.4.0
