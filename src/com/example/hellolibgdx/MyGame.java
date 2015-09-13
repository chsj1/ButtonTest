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

	//����һ��Stage����
	Stage stage;
	//����һ��Button�����
	Button button;
	//���屳��
	Image bgImage;
	//����loadingͼƬ
	Image loadingImage;
	//����һ����ͼƬ�������
	TextureAtlas atlas;
	//������TextureRegion����
	TextureRegion bgRegion;
	//upͼƬ��Ӧ��Region
	TextureRegion btnRegion1;
	//downͼƬ��Ӧ��Region
	TextureRegion btnRegion2;
	//checkedͼƬ��Ӧ��Region
	TextureRegion btnRegion3;
	//loadingͼƬ��Ӧ��Region
	TextureRegion loadingRegion;
	//upͼƬ��Ӧ��Drawable
	TextureRegionDrawable bgDrawable1;
	//downͼƬ��Ӧ��Drawable
	TextureRegionDrawable bgDrawable2;
	//checkedͼƬ��Ӧ��Drawable
	TextureRegionDrawable bgDrawable3;
	
	/**
	 * ��ɳ�ʼ������
	 */
	@Override
	public void create() {
		//stage����ĳ�ʼ��
		stage = new Stage(480, 800, false);
		//��ͼ�ļ��ĳ�ʼ��
		atlas = new TextureAtlas(Gdx.files.internal("data/bofang.atlas"));
		//����Region�ĳ�ʼ��
		bgRegion = atlas.findRegion("bg");
		//loadingͼƬ�ĳ�ʼ��
		loadingRegion = atlas.findRegion("loading");
		//up״̬��region��ʽͼƬ�ĳ�ʼ��
		btnRegion1 = atlas.findRegion("bofang1");
		//down״̬��region��ʽͼƬ�ĳ�ʼ��
		btnRegion2 = atlas.findRegion("bofang2");
		//checked״̬��region��ʽͼƬ�ĳ�ʼ��
		btnRegion3 = atlas.findRegion("bofang3");
		//up״̬��Drawable��ʽͼƬ�ĳ�ʼ��
		bgDrawable1 = new TextureRegionDrawable(btnRegion1);
		//down״̬��Drawable��ʽͼƬ�ĳ�ʼ��
		bgDrawable2 = new TextureRegionDrawable(btnRegion2);
		//checked״̬��Drawable��ʽͼƬ�ĳ�ʼ��
		bgDrawable3 = new TextureRegionDrawable(btnRegion3);
		//����ͼƬ�ĳ�ʼ��
		bgImage = new Image(bgRegion);
		//���ñ���ͼƬ�Ĵ�С
		bgImage.setSize(480, 800);
		//Button����ĳ�ʼ��
		button = new Button(bgDrawable1, bgDrawable2, bgDrawable3);
		//����Button�����λ��
		button.setPosition(240, 300);
		//��ʼ��ͼƬ
		loadingImage = new Image(loadingRegion);
		//����ͼƬ��λ��
		loadingImage.setPosition(240, 400);
		//��ȡTextureRegion �Ŀ��
		float width = loadingRegion.getRegionWidth();
		//��ȡTextureRegion�ĸ߶�
		float height = loadingRegion.getRegionHeight();
		//����ͼƬ��ê��
		loadingImage.setOrigin(width/2, height/2);
		//�ѱ���ͼƬ��ӵ�stage��
		stage.addActor(bgImage);
		//��loadingͼƬ��ӵ�stage��
		stage.addActor(loadingImage);
		//�Ѱ�ť��ӵ�stage��
		stage.addActor(button);
		//����ťע�������
		addListenerOnButton();
		//ʹ��stage�������������ʱ��
		Gdx.input.setInputProcessor(stage);
	}

	/**
	 * ����ťButtonע����������ڿ���loadingͼƬ����ת
	 */
	public void addListenerOnButton(){
		//��buttonע������¼�
		button.addListener(new InputListener(){
			/**
			 * ����ť���µ�ʱ��ִ��
			 */
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				//��ͼƬ���һ����ת����
				loadingImage.addAction(Actions.forever(Actions.rotateBy(-360, 0.5f)));
				return true;
			}
			/**
			 * ����ť�����ʱ��ִ��
			 */
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				//�Ƴ�ͼƬ�еĶ���
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
	 * ÿһ֡����ִ��
	 */
	@Override
	public void render() {
		// ���ñ���Ϊ��ɫ
		Gdx.gl.glClearColor(1, 1, 1, 1);
		// ����
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//����Ա�滭����
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
