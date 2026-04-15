## AWS Commands

- aws cloudformation deploy \
    --template-file ./01_RolesStack.yaml \
    --stack-name <YOUR_STACK_NAME> \
    --capabilities CAPABILITY_NAMED_IAM \
    --region <YOUR_REGION> \
    --profile <YOUR_PROFILE>

- aws cloudformation deploy \
    --template-file ./02_CreateEC2Instances.yaml \
    --stack-name <YOUR_STACK_NAME> \
    --parameter-overrides \
        KeyName=<YOUR_KEY_NAME> \
    --region <YOUR_REGION> \
    --profile <YOUR_PROFILE> \
    --role-arn <YOUR_ROLE_ARN>

- aws cloudformation delete-stack \
              --stack-name ${POLICIES_STACK_NAME} \
              --profile ${CLOUDCHAPS_PROFILE}