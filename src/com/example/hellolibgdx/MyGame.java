package com.example.hellolibgdx;

import android.os.Bundle;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MyGame implements ApplicationListener {

	//定义一个Stage对象
	Stage stage;
	//定义一个Button类对象
	Button button;
	//定义背景
	Image bgImage;
	//定义loading图片
	Image loadingImage;
	//定义一个个图片管理对象
	TextureAtlas atlas;
	//背景的TextureRegion对象
	TextureRegion bgRegion;
	//up图片对应的Region
	TextureRegion btnRegion1;
	//down图片对应的Region
	TextureRegion btnRegion2;
	//checked图片对应的Region
	TextureRegion btnRegion3;
	//loading图片对应的Region
	TextureRegion loadingRegion;
	//up图片对应的Drawable
	TextureRegionDrawable bgDrawable1;
	//down图片对应的Drawable
	TextureRegionDrawable bgDrawable2;
	//checked图片对应的Drawable
	TextureRegionDrawable bgDrawable3;
	
	/**
	 * 完成初始化操作
	 */
	@Override
	public void create() {
		//stage对象的初始化
		stage = new Stage(480, 800, false);
		//合图文件的初始化
		atlas = new TextureAtlas(Gdx.files.internal("data/bofang.atlas"));
		//背景Region的初始化
		bgRegion = atlas.findRegion("bg");
		//loading图片的初始化
		loadingRegion = atlas.findRegion("loading");
		//up状态下region形式图片的初始化
		btnRegion1 = atlas.findRegion("bofang1");
		//down状态下region形式图片的初始化
		btnRegion2 = atlas.findRegion("bofang2");
		//checked状态下region形式图片的初始化
		btnRegion3 = atlas.findRegion("bofang3");
		//up状态下Drawable形式图片的初始化
		bgDrawable1 = new TextureRegionDrawable(btnRegion1);
		//down状态下Drawable形式图片的初始化
		bgDrawable2 = new TextureRegionDrawable(btnRegion2);
		//checked状态下Drawable形式图片的初始化
		bgDrawable3 = new TextureRegionDrawable(btnRegion3);
		//背景图片的初始化
		bgImage = new Image(bgRegion);
		//设置背景图片的大小
		bgImage.setSize(480, 800);
		//Button对象的初始化
		button = new Button(bgDrawable1, bgDrawable2, bgDrawable3);
		//设置Button对象的位置
		button.setPosition(240, 300);
		//初始化图片
		loadingImage = new Image(loadingRegion);
		//设置图片的位置
		loadingImage.setPosition(240, 400);
		//获取TextureRegion 的宽度
		float width = loadingRegion.getRegionWidth();
		//获取TextureRegion的高度
		float height = loadingRegion.getRegionHeight();
		//设置图片的锚点
		loadingImage.setOrigin(width/2, height/2);
		//把背景图片添加到stage上
		stage.addActor(bgImage);
		//把loading图片添加到stage上
		stage.addActor(loadingImage);
		//把按钮添加到stage上
		stage.addActor(button);
		//给按钮注册监听器
		addListenerOnButton();
		//使用stage来处理输入输出时间
		Gdx.input.setInputProcessor(stage);
	}

	/**
	 * 给按钮Button注册监听器用于控制loading图片的旋转
	 */
	public void addListenerOnButton(){
		//给button注册监听事件
		button.addListener(new InputListener(){
			/**
			 * 当按钮按下的时候执行
			 */
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				//给图片添加一个旋转动画
				loadingImage.addAction(Actions.forever(Actions.rotateBy(-360, 0.5f)));
				return true;
			}
			/**
			 * 当按钮弹起的时候执行
			 */
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				//移除图片中的动画
				loadingImage.clearActions();
			}
		});
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}
	
	
	
	/**
	 * 每一帧都会执行
	 */
	@Override
	public void render() {
		// 设置背景为白色
		Gdx.gl.glClearColor(1, 1, 1, 1);
		// 清屏
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//将演员绘画出来
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
