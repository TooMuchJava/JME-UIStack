# JME-UIStack
I made a library for organizing all my various game stages, menus, etc... I give you **UI-Stack**!!!
Why would you even want to use this?

* Every UI has it's own `gui` and `root` nodes.
* Only one UI can be active at a time. You can't display one UI over another.
* As it the title, it's a UI-_Stack_, so you can add things to the stack, _but also remove layers, revealing the ones lower in the stack_.
* Bonus: 8kb(111 lines of code) jarfile

How I used it:
I used to have a really convoluted system for this... Submenus inside the main menu, Attatching everything to a static reference of the root & gui nodes, and ad displays that showed up in the middle of the game (Good way to loose players :P ), So I spent ~an hour making this! It's actually quite easy to implement into a game.

This picture may or may not make what I said harder to understand...
https://www.dropbox.com/s/ayv1dav4wmytrru/IMG_20190210_100324.jpg?dl=0
Download: https://www.dropbox.com/s/ad1hd9t5fftib79/JMEInterface.jar?dl=0
