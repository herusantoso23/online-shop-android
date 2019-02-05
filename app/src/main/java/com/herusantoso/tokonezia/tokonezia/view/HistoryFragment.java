package com.herusantoso.tokonezia.tokonezia.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.herusantoso.tokonezia.tokonezia.R;
import com.herusantoso.tokonezia.tokonezia.adapter.HistoryViewAdapter;
import com.herusantoso.tokonezia.tokonezia.model.History;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HistoryFragment extends Fragment {

    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    Unbinder unbinder;
    private View view;

    private HistoryViewAdapter historyViewAdapter;
    private List<History> histories = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);
        unbinder = ButterKnife.bind(this, view);

        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvHistory.setItemAnimator(new DefaultItemAnimator());
        rvHistory.setAdapter(historyViewAdapter);

        getAllHistory();

        return view;
    }

    private void getAllHistory() {
        History history = new History();
        history.setOrderId("123AZH189JS-01");
        history.setAmount(new BigDecimal(200000));
        history.setVirtualAccount("70001087760586526");
        history.setStatus("Already Paid");
        histories.add(history);

        History history1 = new History();
        history1.setOrderId("123AZH189JS-02");
        history1.setAmount(new BigDecimal(100000));
        history1.setVirtualAccount("70001087760586526");
        history1.setStatus("Waiting for payment");
        histories.add(history);

        History history2 = new History();
        history2.setOrderId("123AZH189JS-02");
        history2.setAmount(new BigDecimal(800000));
        history2.setVirtualAccount("70001087760586526");
        history2.setStatus("Expired");
        histories.add(history2);

        History history3 = new History();
        history3.setOrderId("123AZH189JS-03");
        history3.setAmount(new BigDecimal(100000));
        history3.setVirtualAccount("70001087760586526");
        history3.setStatus("Expired");
        histories.add(history3);

        History history4 = new History();
        history4.setOrderId("123AZH189JS-04");
        history4.setAmount(new BigDecimal(200000));
        history4.setVirtualAccount("70001087760586526");
        history4.setStatus("Waiting for payment");
        histories.add(history4);

        History history5 = new History();
        history5.setOrderId("123AZH189JS-05");
        history5.setAmount(new BigDecimal(700000));
        history5.setVirtualAccount("70001087760586526");
        history5.setStatus("Already Paid");
        histories.add(history5);

        historyViewAdapter = new HistoryViewAdapter(getActivity(), histories);
        rvHistory.setAdapter(historyViewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
