package com.petaurus.league.ui.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;

import java.io.IOException;
import java.io.InputStream;

public class SvgDecoder extends BaseImageDecoder {

  private static final String TAG = SvgDecoder.class.getSimpleName();

  /**
   * @param loggingEnabled Whether debug logs will be written to LogCat. Usually should match {@link
   *                       ImageLoaderConfiguration.Builder#writeDebugLogs()
   *                       ImageLoaderConfiguration.writeDebugLogs()}
   */
  public SvgDecoder(boolean loggingEnabled) {
	super(loggingEnabled);
  }

  @Override
  public Bitmap decode(ImageDecodingInfo imageDecodingInfo) throws IOException {
	Log.d(TAG, "decode: " + imageDecodingInfo.getOriginalImageUri());
	if (!imageDecodingInfo.getOriginalImageUri().endsWith(".svg")) {
	  return super.decode(imageDecodingInfo);
	}

	InputStream is = imageDecodingInfo.getDownloader().getStream(
			imageDecodingInfo.getImageUri(), imageDecodingInfo.getExtraForDownloader());


	SVG svg;
	try {
	  svg = SVG.getFromInputStream(is);
	} catch (SVGParseException e) {
	  Log.w(TAG, "decode: ", e);
	  return super.decode(imageDecodingInfo);
	}

	Bitmap image = null;
	if (svg.getDocumentWidth() != -1) {
	  image = Bitmap.createBitmap((int) Math.ceil(svg.getDocumentWidth()),
			  (int) Math.ceil(svg.getDocumentHeight()),
			  Bitmap.Config.ARGB_8888);
	  Canvas bmcanvas = new Canvas(image);
	  bmcanvas.drawRGB(255, 255, 255);
	  svg.renderToCanvas(bmcanvas);
	}

	return image;
  }
}