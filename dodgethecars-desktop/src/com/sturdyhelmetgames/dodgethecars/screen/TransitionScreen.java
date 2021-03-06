/*
 * Copyright 2012 Antti Kolehmainen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sturdyhelmetgames.dodgethecars.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.sturdyhelmetgames.dodgethecars.DodgeTheCarsGame;

/**
 * Screen that supports simple transitions.
 * 
 * @author Antti 28.6.2012
 * 
 */
public abstract class TransitionScreen extends BasicScreen {

	protected float screenTime;
	private float fadeAlpha = 1f;
	protected Color transitionColor = new Color();
	private final ShapeRenderer renderer = new ShapeRenderer();

	private static final float FADE_OUT_START_TIME = 4f;

	public TransitionScreen(DodgeTheCarsGame game) {
		super(game);
	}

	@Override
	protected void updateScreen(float fixedStep) {
		screenTime += fixedStep;
	}

	@Override
	public void renderScreen(float delta) {
		renderFadeIn(delta);
		renderFadeOut(delta);
	}

	/**
	 * Renders fade in transition.
	 * 
	 * @param delta
	 *            delta time
	 */
	protected void renderFadeIn(float delta) {
		// a simple fade in transition
		if (screenTime > -1f && screenTime < 0.99f) {
			transitionColor.set(0f, 0f, 0f, fadeAlpha -= delta);

			Gdx.gl.glEnable(GL20.GL_BLEND);
			renderer.begin(ShapeType.FilledRectangle);
			renderer.filledRect(0f, 0f, Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight(), transitionColor, transitionColor,
					transitionColor, transitionColor);
			renderer.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);
		}
	}

	/**
	 * Renders fade out transition.
	 * 
	 * @param delta
	 *            delta time
	 */
	protected void renderFadeOut(float delta) {
		if (screenTime > FADE_OUT_START_TIME) {
			transitionColor.set(0f, 0f, 0f, fadeAlpha += delta);

			Gdx.gl.glEnable(GL20.GL_BLEND);
			renderer.begin(ShapeType.FilledRectangle);
			renderer.filledRect(0f, 0f, Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight(), transitionColor, transitionColor,
					transitionColor, transitionColor);
			renderer.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		renderer.dispose();
	}

}
