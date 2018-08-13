package com.potato.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.potato.adapter.OnlineAdapter;
import com.potato.data.OnlineListItem;
import com.potato.potatoplayer.R;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiAudio;
import com.vk.sdk.api.model.VKList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class OnlineFragment extends Fragment {

    OnlineAdapter onlineAdapter;
    ArrayList<OnlineListItem> onlineListItems;

    @BindView(R.id.main_online_item_list)
    RecyclerView mainOnlineItemList;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_online, container, false);
        unbinder = ButterKnife.bind(this, view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mainOnlineItemList.setLayoutManager(layoutManager);

        onlineListItems = new ArrayList<>();
        VKRequest online = VKApi.audio().getPopular(VKParameters.from(VKApiConst.COUNT, 15));

        online.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                VKList<VKApiAudio> list = (VKList) response.parsedModel;

                for (VKApiAudio apiAudio : list){
                    onlineListItems.add(new OnlineListItem(apiAudio.title));
                }
                onlineAdapter = new OnlineAdapter(onlineListItems);
                mainOnlineItemList.setAdapter(onlineAdapter);
            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
