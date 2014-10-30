phpspec plugin for PhpStorm IDE
===

> be aware that building & testing is kind of annoying...
> If you find a solution that includes testing without
> restarting the ide, please tell me

Building / Development:
---

1. Setup IDEA for Plugin Development: http://confluence.jetbrains.com/display/IntelliJIDEA/Prerequisites
2. Add all entries from lib
3. set the libs aspectjrt-1.7.3.jar and gson-2.0.jar to be exported and compliled.
4. set the other libs to be provided
5. you should now be able to Build the Plugin: **Build > Prepare Plugin Module <...> For Deployment**


Notes:
---

I basically merged https://github.com/mprzytulski/phpstorm-phpspec and https://github.com/mprzytulski/phpstorm-commons
-> all the work is comming from @mprzytulski and I'm just bugfixing / providing an info of how to build the plugin


check the following project: https://github.com/vivait/phpspec-phpstorm-file-watcher
-> this adds a file watcher that runs phpspec automatically when you work on a file
