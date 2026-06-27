
// ===================== Driver Class =====================
public class Inheritance_2 {
    public static void main(String[] args) {

        // ---------- 3 Member Objects ----------
        Member m1 = new Member("Ahmed Raza", "ahmed@gmail.com", "0300-1111111");
        Member m2 = new Member("Sara Khan",  "sara@gmail.com",  "0300-2222222");
        Member m3 = new Member("Usman Ali",  "usman@gmail.com", "0300-3333333");

        // ---------- 2 CurrentAccount Objects ----------
        CurrentAccount ca1 = new CurrentAccount("Current Account", 1001, "Karachi Branch",   m1, 150000.00);
        CurrentAccount ca2 = new CurrentAccount("Current Account", 1002, "Lahore Branch",    m2, 200000.00);

        // ---------- 1 SavingAccount Object ----------
        SavingAccount  sa1 = new SavingAccount ("Saving Account",  2001, "Islamabad Branch", m3,  75000.00);

        // ==================== Display ====================
        System.out.println("============================================");
        System.out.println("        CURRENT ACCOUNT HOLDERS             ");
        System.out.println("============================================");

        System.out.println("\n>>> Current Account 1:");
        ca1.DisplayInfo();

        System.out.println("\n>>> Current Account 2:");
        ca2.DisplayInfo();

        System.out.println("\n============================================");
        System.out.println("         SAVING ACCOUNT HOLDER              ");
        System.out.println("============================================");

        System.out.println("\n>>> Saving Account 1:");
        sa1.DisplayInfo();

        System.out.println("\n============================================");
    }
}

// ===================== Member Class =====================
class Member {
    private String MemberName;
    private String MemberEmail;
    private String MemberContact;

    public Member(String MemberName, String MemberEmail, String MemberContact) {
        this.MemberName    = MemberName;
        this.MemberEmail   = MemberEmail;
        this.MemberContact = MemberContact;
    }

    public String GetMemberName()    { return MemberName;    }
    public String GetMemberEmail()   { return MemberEmail;   }
    public String GetMemberContact() { return MemberContact; }
}

// ===================== Account Class (Parent) =====================
class Account {
    private int    AccountNo;
    private Member AccountOwner;
    private String AccountBranch;
    private double AccountBalance;

    // FIX: Parameter order matches UML → (int, String, Member, double)
    public Account(int AccountNo, String AccountBranch, Member AccountOwner, double AccountBalance) {
        this.AccountNo      = AccountNo;
        this.AccountBranch  = AccountBranch;
        this.AccountOwner   = AccountOwner;
        this.AccountBalance = AccountBalance;
    }

    public String GetOwnerName()     { return AccountOwner.GetMemberName();    }
    public String GetOwnerEmail()    { return AccountOwner.GetMemberEmail();   }
    public String GetOwnerContact()  { return AccountOwner.GetMemberContact(); }
    public String GetAccountBranch() { return AccountBranch;  }
    public int    GetAccountNo()     { return AccountNo;       }
    public double GetAccountBalance(){ return AccountBalance;  }
}

// ===================== SavingAccount Class (Child of Account) =====================
class SavingAccount extends Account {
    private String AccountType;

    // FIX: super() call order matches Account constructor (int, String, Member, double)
    public SavingAccount(String AccountType, int AccountNo, String AccountBranch,
                         Member AccountOwner, double AccountBalance) {
        super(AccountNo, AccountBranch, AccountOwner, AccountBalance);
        this.AccountType = AccountType;
    }

    public String GetAccountType() { return AccountType; }

    public void DisplayInfo() {
        System.out.println("  Account Type  :  " + AccountType);
        System.out.println("  Account No    :  " + GetAccountNo());
        System.out.println("  Branch        :  " + GetAccountBranch());
        System.out.printf ("  Balance       :  %.2f%n", GetAccountBalance()); // FIX: proper formatting
        System.out.println("  Owner Name    :  " + GetOwnerName());
        System.out.println("  Owner Email   :  " + GetOwnerEmail());
        System.out.println("  Owner Contact :  " + GetOwnerContact());
    }
}

// ===================== CurrentAccount Class (Child of Account) =====================
class CurrentAccount extends Account {
    private String AccountType;

    // FIX: super() call order matches Account constructor (int, String, Member, double)
    public CurrentAccount(String AccountType, int AccountNo, String AccountBranch,
                          Member AccountOwner, double AccountBalance) {
        super(AccountNo, AccountBranch, AccountOwner, AccountBalance);
        this.AccountType = AccountType;
    }

    public String GetAccountType() { return AccountType; }

    public void DisplayInfo() {
        System.out.println("  Account Type  :  " + AccountType);
        System.out.println("  Account No    :  " + GetAccountNo());
        System.out.println("  Branch        :  " + GetAccountBranch());
        System.out.printf ("  Balance       :  %.2f%n", GetAccountBalance()); // FIX: proper formatting
        System.out.println("  Owner Name    :  " + GetOwnerName());
        System.out.println("  Owner Email   :  " + GetOwnerEmail());
        System.out.println("  Owner Contact :  " + GetOwnerContact());
    }
}
