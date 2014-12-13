package com.bloc.blocnotes.models;


import java.util.HashMap;
import java.util.Map;

public abstract class ModelCentre<T extends Model> {

    private String mTableName;
    private Map<Long, T> mModels;

    public ModelCentre(String table) {

        mTableName = table;
        mModels = new HashMap<Long, T>();

    }


    public Model get(long id, boolean lazyLoad) {

        T model = null;

        if (mModels.containsKey(String.valueOf(id))) {

            // we already have this model loaded...get it from the map
            model = mModels.get(String.valueOf(id));
        }
        else {

            // we don't have a copy of this model yet
            model = _createModel(id);
        }

        // if we're not lazy loading and the model is not already loaded - load it
        if (!lazyLoad && !model.isLoaded()) {
            model.load();
        }

        return model;
    }

    protected abstract T _createModel(long id);
}
