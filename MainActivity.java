public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
        LoaderManager.LoaderCallbacks<Cursor> {
    
    private GoogleMap mMap;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        
        mMap.setOnMapClickListener(latLng -> {
            new LocationInsertTask().execute(latLng);
        });

        mMap.setOnMapLongClickListener(latLng -> {
            new LocationDeleteTask().execute();
        });
    }

    private class LocationInsertTask extends AsyncTask<LatLng, Void, Void> {
        @Override
        protected Void doInBackground(LatLng... latLngs) {
            LatLng latLng = latLngs[0];
            ContentValues values = new ContentValues();
            values.put(LocationsDB.FIELD_LAT, latLng.latitude);
            values.put(LocationsDB.FIELD_LNG, latLng.longitude);
            values.put(LocationsDB.FIELD_ZOOM, mMap.getCameraPosition().zoom);
            getContentResolver().insert(LocationsContentProvider.CONTENT_URI, values);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            getSupportLoaderManager().restartLoader(0, null, MainActivity.this);
        }
    }

    private class LocationDeleteTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            getContentResolver().delete(LocationsContentProvider.CONTENT_URI, null, null);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mMap.clear();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, LocationsContentProvider.CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mMap.clear();
        while (data.moveToNext()) {
            double lat = data.getDouble(data.getColumnIndex(LocationsDB.FIELD_LAT));
            double lng = data.getDouble(data.getColumnIndex(LocationsDB.FIELD_LNG));
            float zoom = data.getFloat(data.getColumnIndex(LocationsDB.FIELD_ZOOM));
            LatLng latLng = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mMap.clear();
    }
}
