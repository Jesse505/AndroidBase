package ${packageName}

import android.os.Bundle
import ${applicationPackage}.R
import ${packageName}.contract.${contractName}Contract
import ${packageName}.presenter.${presenterName}Presenter
import com.jesse.baselibs.base.BaseMvpActivity
import com.jesse.baselibs.mvp.CreatePresenter
import com.jesse.baselibs.mvp.PresenterVariable

@CreatePresenter(presenter = [${presenterName}Presenter::class])
class ${activityName}Activity : BaseMvpActivity(), ${contractName}Contract.View {

    @PresenterVariable
    internal var mPresenter: ${presenterName}Presenter? = null

    override fun getLayoutId(): Int {
        return R.layout.${activityLayout}
    }

    override fun init(savedInstanceState: Bundle?) {

    }
}
