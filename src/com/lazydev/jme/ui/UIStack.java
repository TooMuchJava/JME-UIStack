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
import java.util.ArrayList;
import java.util.List;

public class UIStack {
    public List<AbstractUI> stack = new ArrayList(), added = new ArrayList();
    public Node rootNode = new Node(), guiNode = new Node();
    public int screenWidth, screenHeight;
    public AssetManager assetManager;
    public UIStack(AssetManager assetManager, int screenWidth, int screenHeight) {
        this.assetManager = assetManager;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }
    public void addUI(AbstractUI ui) {
        added.add(ui);
        ui.attatchToStack(this);
        ui.onAdd();
    }
    public void addAndViewUI(AbstractUI ui) {
        addUI(ui);
        viewUI(ui);
    }
    public void removeUI(AbstractUI ui) {
        if (added.contains(ui)) {
            added.remove(ui);
            return;
        }
        if (stack.indexOf(ui) - 1 >= 0) viewUI(stack.get(stack.indexOf(ui) - 1));
        _removeUI(ui);
    }
    protected void _removeUI(AbstractUI ui) {
        _shelveUI(ui);
        stack.remove(ui);
        ui.onRemove();
    }
    protected void _viewUI(AbstractUI ui) {
        ui.onView();
        rootNode.attachChild(ui.rootNode);
        guiNode.attachChild(ui.guiNode);
        if (added.contains(ui)) added.remove(ui);
        stack.remove(ui);
        stack.add(ui);
    }
    public void viewUI(AbstractUI ui) {
        if (stack.size() - 1 >= 0) _shelveUI(stack.get(stack.size() - 1));
        _viewUI(ui);
    }
    protected void _shelveUI(AbstractUI ui) {
        ui.onShelve();
        rootNode.detachChild(ui.rootNode);
        guiNode.detachChild(ui.guiNode);
    }
    protected void returnToUI(AbstractUI ui) {
        int index = stack.indexOf(ui), currentIndex = stack.size();
        for (int idx = stack.size(); idx < index; idx--) {
            _removeUI(stack.get(idx));
        }
        _viewUI(ui);
    }
    public void removeUI() {
        removeUI(stack.get(stack.size() - 1));
    }
    public void swapUI(AbstractUI ui1, AbstractUI ui2) {
        int index = stack.indexOf(ui1);
        stack.set(index, ui2);
        _removeUI(ui1);
        addUI(ui2);
        if (index == stack.size() - 1) {
            _viewUI(ui2);
        }
    }
    public void update(float tpf) {
        if (!stack.isEmpty()) stack.get(stack.size() - 1).update(tpf);
        
    }
    public AbstractUI getNearestUI(Class<? extends AbstractUI> clazz) {
        for (int i = stack.size() - 1; i > 0; i--) {
            if (stack.get(i).getClass() == clazz) return stack.get(i);
        }
        return null;
    }
}