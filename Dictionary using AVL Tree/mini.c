#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

typedef struct avlnode* position;
typedef struct avlnode* avltree;
#define MAX 100

struct avlnode{
	char english[MAX],mean[MAX];
	avltree left;
	avltree right;
	int height;
};

int max(int a,int b){
	if(a>b)
		return a;
	else
		return b;
}

static int height(position p){
	if(p == NULL)
		return -1;
	else 
		return p->height;
}

position singlerotatewithleft(position k2){
	position k1;
	k1 = k2->left;
	k2->left = k1->right;
	k1->right = k2;
	k2->height = max(height(k2->left),height(k2->right))+1;
	k1->height = max(height(k1->left),k2-> height)+1;
	return k1;
}

position singlerotatewithright(position k2){
	position k1;
	k1 = k2->right;
	k2->right = k1->left;
	k1->left = k2;
	k2->height = max(height(k2->left),height(k2->right))+1;
	k1->height = max(height(k1->right),k2-> height)+1;
	return k1;
}

position doublerotatewithleft(position k3){
	k3->left = singlerotatewithright(k3->left);
	k3 = singlerotatewithleft(k3);
	return k3;
}

position doublerotatewithright(position k3){
	k3->right = singlerotatewithleft(k3->right);
	k3 = singlerotatewithright(k3);
	return k3;
}

avltree insert(avltree t, char english[], char mean[]){
	if(t==NULL){
		t = (avltree) malloc(sizeof(struct avlnode));
		strcpy(t->english,english);
		strcpy(t->mean,mean);
		t->height = 0;
		t->left = t->right = NULL;
	}
	else if(strcasecmp(english,t->english)<0){
		t->left = insert(t->left,english,mean);
		if(height(t->left)-height(t->right)==2){
			if(strcasecmp(english,t->left->english)<0)
				t = singlerotatewithleft(t);
			else
				t = doublerotatewithleft(t);
		}
	}
	else if(strcasecmp(english,t->english)>0){
		t->right = insert(t->right,english,mean);
		if(height(t->right)-height(t->left)==2){
			if(strcasecmp(english,t->right->english)>0)
				t = singlerotatewithright(t);
			else
				t = doublerotatewithright(t);
		}
	}
	t->height = max(height(t->left),height(t->right)) + 1;
	return t;
}

int getBalance(avltree N){ 
    if (N == NULL) 
        return 0; 
    return height(N->left) - height(N->right); 
} 

avltree minValueNode(avltree node){ 
    avltree current = node; 

    while (current->left != NULL) 
        current = current->left; 
  
    return current; 
} 

avltree deleteNode(avltree root, char key[]){   
    if (root == NULL) 
        return root; 
  
    if (strcasecmp(key,root->english)<0) 
        root->left = deleteNode(root->left, key); 
 
    else if(strcasecmp(key,root->english)>0) 
        root->right = deleteNode(root->right, key); 

    else{
    //printf("%s\n",root->english); 
        if( (root->left == NULL) || (root->right == NULL) ) { 
            avltree temp = root->left ? root->left : 
                                             root->right; 
  
            if (temp == NULL){ 
                temp = root; 
                root = NULL; 
            } 
            else
             *root = *temp;
            free(temp); 
        } 

        else{ 
            avltree temp = minValueNode(root->right); 

            strcpy(root->english,temp->english); 
            strcpy(root->mean,temp->mean);

            root->right = deleteNode(root->right, temp->english); 
        } 
    } 
  
    if (root == NULL) 
      return root; 
 
    root->height = 1 + max(height(root->left), 
                           height(root->right)); 

    int balance = getBalance(root); 

    if (balance > 1 && getBalance(root->left) >= 0) 
        return singlerotatewithright(root); 
   
    if (balance > 1 && getBalance(root->left) < 0){ 
        root->left =  singlerotatewithleft(root->left); 
        return singlerotatewithright(root); 
    } 
  
    if (balance < -1 && getBalance(root->right) <= 0) 
        return singlerotatewithleft(root); 
 
    if (balance < -1 && getBalance(root->right) > 0){ 
        root->right = singlerotatewithright(root->right); 
        return singlerotatewithleft(root); 
    } 
  
    return root; 
} 

position find(avltree t,char word[]){
	if(t==NULL)
		return NULL;
	else if(strcasecmp(word,t->english)<0)
		return find(t->left,word);
	else if(strcasecmp(word,t->english)>0)
		return find(t->right,word);
	else
		return t;
}

void inorder(avltree t,char op){
	if(t!=NULL){
		inorder(t->left,op);
		if(t->english[0] == op)
			printf("%s : %s\n",t->english,t->mean);
		inorder(t->right,op);
	}
}

int findMeaning(avltree tree, char s[]){
	position f = find(tree,s);

	if(f!=NULL){
		printf("%s : %s\n",s,f->mean);
		return 0;
	}
	else
		printf("Sorry %s is not in the dictionary...\n",s);
	return -1;
}

avltree add(avltree t, char word[]){
	position f = find(t,word);

	if(f==NULL){
		printf("\nEnter the meaning of the given word : ");
		char mean[MAX];
		scanf(" %[^\n]",mean);
		t = insert(t,word,mean);
		printf("\n\nThe word was successfully added.");
	}
	else{
		printf("\n\nSorry the word already exists...");
	}
	return t;
}

avltree search(avltree t){
	char word[MAX];
	printf("Enter the word to be searched for : ");
	scanf("%s",word);
	int ch = findMeaning(t,word);
	if(ch == -1){
		printf("\n\nDo you want to add the word to the dictionary? (1/0)\n");
		scanf("%d",&ch);
		if(ch == 1)
			t = add(t,word);
	}
	return t;
}

void dispall(avltree t){
	getchar();
	for(char op = 'a'; op <='z'; op++){
		system("clear");
		inorder(t,op);
		printf("\nThat's all the words starting with %c",op);
		printf("\n\nPRESS ENTER TO CONTINUE...");
		getchar();
	}
}

void display(avltree t){
	char op;
	printf("Enter the starting alphabet : ");
	scanf(" %c",&op);
	system("clear");
	inorder(t,op);
}

avltree createdict(avltree tree){
	FILE *fptr;
	fptr = fopen("dict.txt","r");
	char word[MAX],mean[MAX];
	
	while(fscanf(fptr,"%s",word) != EOF){
		//fscanf(fptr,"%s",word);
		fscanf(fptr," %[^\n]",mean);
		tree = insert(tree,word,mean);
	}

	fclose(fptr);
	return tree;
}

void writeout(avltree t,FILE *fp){
	if(t!=NULL){
		writeout(t->left,fp);
		fprintf(fp,"%s %s\n",t->english,t->mean);
		writeout(t->right,fp);
	}
}

int main(void){
	system("clear");
	printf("SETTING UP THE DICTIONARY...\n");
	sleep(1);
	avltree tree = NULL;
	tree = createdict(tree);
	printf("\n\nTHE DICTIONARY IS SET UP...\n");
	printf("\n\nPRESS ENTER TO CONTINUE...");
	getchar();
	//inorder(tree);

	int op = 1;
	while(op != 0){
		sleep(1);
		system("clear");
		printf("0 - Exit the dictionary\n1 - Search for a word.\n2 - Add a word.\n3 - Delete a word\n");
		printf("4 - Display all the words.\n5 - Display words starting with a given alphabet.\n");
		printf("Choose : ");
		scanf("%d",&op);
		char word[MAX];
		printf("\n");
		switch(op){
			case 1 : tree = search(tree); break;
			case 2 : {
				printf("Enter the word to be added : ");
				scanf("%s",word);
				tree = add(tree,word);
			} break;
			case 3 : {
				printf("Enter the word to be deleted : ");
				scanf("%s",word);
				position f = find(tree,word);
				if(f!=NULL){ 
					tree = deleteNode(tree,word);
					printf("\n\nThe word was successfully deleted.");
				}
				else{
					printf("Sorry %s is not in the dictionary...\n",word);
				}
			} break;
			case 4 : dispall(tree); break;
			case 5 : display(tree); break;
			case 0 : break;
			default : printf("INCORRECT OPTION... CHOOSE AGAIN : ");
		}
		if(op !=4 ){
			printf("\n\nPRESS ENTER TO CONTINUE...");
			getchar();
			getchar();
		}
	}
	system("clear");
	FILE *fptr;
	fptr = fopen("dict.txt","w");
	printf("SAVING THE DICTIONARY...\n");
	sleep(1);
	writeout(tree,fptr);
	printf("\n\nTHE DICTIONARY IS SAVED...\n");
	fclose(fptr);
}