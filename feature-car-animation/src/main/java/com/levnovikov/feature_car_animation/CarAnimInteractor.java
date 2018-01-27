package com.levnovikov.feature_car_animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.levnovikov.feature_car_animation.di.CarAnimScope;
import com.levnovikov.feature_car_animation.map_wrapper.MapWrapper;
import com.levnovikov.feature_map.dependency.MapProvider;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.node_state.ActivityState;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

@CarAnimScope
public class CarAnimInteractor extends Interactor<CarAnimRouter> {

    private final MapProvider mapProvider;
    private final VectorDrawable carDrawable;

    @Nullable
    private Marker car;

    @Inject
    public CarAnimInteractor(
            CarAnimRouter router,
            ActivityState activityState,
            MapProvider mapProvider,
            VectorDrawable carDrawable) {
        super(router, activityState);
        this.mapProvider = mapProvider;
        this.carDrawable = carDrawable;
    }

    @Override
    public void onGetActive() {
        super.onGetActive();
        mapProvider.getMap()
                .map(MapWrapper::new)
                .subscribe(map -> {
                    map.init();
                    car = map.showCar(createCar(new LatLng(47.571758, -122.313447), 0));
                    map.focusOnCar(car);
                }, e -> { });
    }

    private MarkerOptions createCar(LatLng latLng, float rotation) {
        return new MarkerOptions()
                .icon(getBitmapDescriptor(R.drawable.ic_vehicle_taxi))
                .position(latLng)
                .rotation(rotation);
    }

    private BitmapDescriptor getBitmapDescriptor(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int h = carDrawable.getIntrinsicHeight();
            int w = carDrawable.getIntrinsicWidth();

            carDrawable.setBounds(0, 0, w, h);

            Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bm);
            carDrawable.draw(canvas);

            return BitmapDescriptorFactory.fromBitmap(bm);

        } else {
            return BitmapDescriptorFactory.fromResource(id);
        }
    }

//    BitmapDescriptor generateBitmapDescriptor(Context context, int drawable, InputStream inputStream) {
//        if (inputStream != null) {
//            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            if (bitmap != null) {
//                final BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    Log.d("LOG", "generateBitmapDescriptor: inputStream close exception");
//                }
//                return bitmapDescriptor;
//            }
//        }
//        return createBitmapDescriptor(context, drawable);
//    }
//
//    BitmapDescriptor createBitmapDescriptor(InputStream inputStream) {
//        try {
//            return BitmapDescriptorFactory.fromBitmap(getBitmap(inputStream));
//        } catch (Throwable t) {
//            return null;
//        }
//    }
//
//    BitmapDescriptor createBitmapDescriptor(Context context, int drawableId) {
//        final Bitmap bitmap = getBitmap(context, drawableId);
//        if (bitmap == null) {
//            return null;
//        } else {
//            return BitmapDescriptorFactory.fromBitmap(bitmap);
//        }
//    }
//
//
//    private Bitmap getBitmap(Context context, int drawableId) {
//        final Drawable drawable = AppCompatResources.getDrawable(context, drawableId);
//        if (drawable == null) return null;
//        if (drawable instanceof BitmapDrawable) {
//            return ((BitmapDrawable) drawable).getBitmap();
//        } else {
//            return getBitmap(drawable);
//        }
//    }
//
//    private Bitmap getBitmap(Drawable vectorDrawable) {
//        final Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
//                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//        final Canvas canvas = new Canvas(bitmap);
//        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//        vectorDrawable.draw(canvas);
//        return bitmap;
//    }
//
//    private Bitmap getBitmap(InputStream inputStream) {
//        return BitmapFactory.decodeStream(inputStream);
//    }
}
