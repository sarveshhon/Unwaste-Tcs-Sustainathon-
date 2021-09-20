package com.teamunwaste.unwaste.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamunwaste.unwaste.Home.ExploreFM.ExploreAdapter;
import com.teamunwaste.unwaste.Home.ExploreFM.ExploreModel;
import com.teamunwaste.unwaste.databinding.FragmentExploreBinding;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment {

    FragmentExploreBinding binding;
    List<ExploreModel> exploreModelList = new ArrayList<>();
    ExploreAdapter exploreAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExploreBinding.inflate(getLayoutInflater());

        loadExploreData();

        return binding.getRoot();
    }

    private void loadExploreData() {

        exploreModelList.add(new ExploreModel("Plastic DIY", "https://www.thestatesman.com/wp-content/uploads/2019/09/plastic-1.jpg"));
        exploreModelList.add(new ExploreModel("Paper DIY", "https://tiimg.tistatic.com/fp/1/006/911/printed-occ-waste-paper-348.jpg"));
        exploreModelList.add(new ExploreModel("Glass DIY", "https://www.greenappsandweb.com/wp-content/uploads/2016/02/Mr-Iglu-app.jpg"));
        exploreModelList.add(new ExploreModel("Metal DIY", "https://www.thebalancesmb.com/thmb/Kkb0EAVcLU86qfGL2xHc_xDJCf8=/2448x1836/smart/filters:no_upscale()/GettyImages-678449073-58161f435f9b581c0bfddacc.jpg"));
        exploreModelList.add(new ExploreModel("E Waste DIY","https://www.pnas.org/content/116/3/711/F1.large.jpg"));
        exploreModelList.add(new ExploreModel("Organic Waste DIY","https://images.squarespace-cdn.com/content/v1/5ad225bf75f9eeb22cb1e767/1535599095940-YTYEW824QJM4QOFFL4L9/Plate+Waste+Recycling+Organic+Palm+Springs"));

        exploreAdapter = new ExploreAdapter(getContext(), exploreModelList);
        binding.rvExplore.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.rvExplore.setAdapter(exploreAdapter);


    }

}