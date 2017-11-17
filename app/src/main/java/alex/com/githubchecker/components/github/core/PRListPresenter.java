package alex.com.githubchecker.components.github.core;

import android.content.Context;
import android.content.Intent;

import alex.com.githubchecker.components.github.activities.PRDiffActivity;
import alex.com.githubchecker.utils.SchedulerUtils;

/**
 * Created by Alex on 11/11/2017.
 */

public class PRListPresenter {

    private PRListView view;
    private GithubRepoModel model;

    public PRListPresenter(GithubRepoModel model, PRListView view) {
        this.model = model;
        this.view = view;
    }

    public void onCreate() {

        //Click listeners
        view.refreshClicks().subscribe(obj -> {
            refreshPullRequests();
        });
        view.selectedPRSubject().subscribe(this::pullRequestTapped);

        //Model->View listeners
        model.getPullRequestsSubject()
                .observeOn(SchedulerUtils.main())
                .subscribe(view::bindPRList);
    }

    public void onStart() {
        if (!model.getPullRequestsSubject().hasValue()) {
            refreshPullRequests();
        }
    }

    private void pullRequestTapped(Integer pullrequestId) {
        PRDiffActivity.Show(view.view().getContext(), pullrequestId);
    }

    private void refreshPullRequests() {
        view.showLoading(true);
        model.getPullRequests();
    }

}
