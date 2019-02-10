/*
 * The MIT License
 *
 * Copyright 2019 Indigo Amann.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.lazydev.jme.ui;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
public abstract class AbstractUI {
    public Node rootNode = new Node(), guiNode = new Node();
    public UIStack stack;
    public void attatchToStack(UIStack stack) {
        this.stack = stack;
    }
    public void onAdd() {};
    public void onView() {}
    public void update(float tpf) {};
    public void onShelve() {}
    public void onRemove() {};
    public void removeFromStack() {
        stack.removeUI(this);
    }
    public int getScreenWidth() {
        return stack.screenWidth;
    }
    public int getScreenHeight() {
        return stack.screenHeight;
    }
    public AssetManager getAssetManager() {
        return stack.assetManager;
    }
}
