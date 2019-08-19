package ${packageName}.presenter;

import ${packageName}.contract.${contractName}Contract;
import ${packageName}.model.${modelName}Model;
import com.jesse.baselibs.base.BasePresenter;

public class ${presenterName}Presenter extends BasePresenter<${contractName}Contract.View> implements ${contractName}Contract.Presenter {

        private ${modelName}Model mModel;

        public ${presenterName}Presenter() {
            mModel = new ${modelName}Model();
        }

}