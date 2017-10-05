package github.com.Zerocheng.BreatheSpoof;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

/**
Author:Zero
E-Mail:2767832980@qq.com
*/

public class MainActivity extends Activity
	{
		@Override//这里屏蔽了多任务键，应该有的手机屏蔽不了吧
		protected void onPause ( )
			{
				super.onPause ( );
				ActivityManager activitymanager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
				activitymanager.moveTaskToFront(getTaskId(),0);
			}
    @Override                                                              
    protected void onCreate(Bundle savedInstanceState)
    {
        //隐藏状态栏
		Window window=getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//设置布局
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		//隐藏标题栏
		ActionBar ab = getActionBar();
		ab.hide();
		
		//媒体音量最大化
		AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolume , 0);
		
		//播放mp3
		final MediaPlayer mMediaPlayer=MediaPlayer.create ( this, R.raw.music );
		mMediaPlayer.start ( );
		
		//循环播放
		mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() 
		{   
			@Override
			public void onCompletion(MediaPlayer v)
			{  
			mMediaPlayer.start();  
			mMediaPlayer.setLooping(true);  
			}
		});
	}
@Override//这里就这样他妈的屏蔽了返回键，音量上下键
public boolean onKeyDown ( int keyCode, KeyEvent event )
	{
		return true;
		//return super.onKeyDown ( keyCode, event );
	}
}
