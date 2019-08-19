package ${packageName};

import android.os.Bundle;
import ${applicationPackage}.R;
import ${packageName}.contract.${contractName}Contract;
import ${packageName}.presenter.${presenterName}Presenter;
import com.jesse.baselibs.base.BaseMvpActivity;
import com.jesse.baselibs.mvp.CreatePresenter;
import com.jesse.baselibs.mvp.PresenterVariable;
import org.jetbrains.annotations.Nullable;

@CreatePresenter(presenter = ${presenterName}Presenter.class)
public class ${activityName}Activity extends BaseMvpActivity implements ${contractName}Contract.View{

    @PresenterVariable
    ${presenterName}Presenter mPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.${activityLayout};
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

    }

}
