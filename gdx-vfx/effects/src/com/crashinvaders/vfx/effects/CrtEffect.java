package com.crashinvaders.vfx.effects;

import com.crashinvaders.vfx.common.framebuffer.FboWrapper;
import com.crashinvaders.vfx.PostProcessorEffect;
import com.crashinvaders.vfx.filters.CrtFilter;

public class CrtEffect extends PostProcessorEffect {

    private final CrtFilter crtFilter;

    public CrtEffect() {
        crtFilter = new CrtFilter();
    }

    public CrtEffect(CrtFilter.LineStyle lineStyle, float scanLineBrightness0, float scanLineBrightness1) {
        crtFilter = new CrtFilter(lineStyle, scanLineBrightness0, scanLineBrightness1);
    }

    @Override
    public void resize(int width, int height) {
        crtFilter.resize(width, height);
    }

    @Override
    public void rebind() {
        crtFilter.rebind();
    }

    @Override
    public void render(FboWrapper src, FboWrapper dest) {
        crtFilter.setInput(src).setOutput(dest).render();
    }

    @Override
    public void dispose() {
        crtFilter.dispose();
    }

    public CrtFilter.SizeSource getSizeSource() {
        return crtFilter.getSizeSource();
    }

    public CrtEffect setSizeSource(CrtFilter.SizeSource sizeSource) {
        crtFilter.setSizeSource(sizeSource);
        return this;
    }
}
