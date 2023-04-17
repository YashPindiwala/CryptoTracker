package com.project.app.cryptotracker.ViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.project.app.cryptotracker.Dashboard.DetailFragment;
import com.project.app.cryptotracker.Dashboard.EducationFragment;
import com.project.app.cryptotracker.R;

public class EducationViewpagerAdapter extends FragmentStateAdapter {
    // TODO: add arraylist datasource to class properties and constructor
    public EducationViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return EducationFragment.newInstance("What is the Blockchain?", "Blockchain is a decentralized and distributed digital ledger that records transactions across multiple computers in a secure and transparent manner. " +
                        "It maintains a continuously growing list of records, called blocks, which are linked and secured using cryptography. Each block contains a timestamp and a link to a previous block, ensuring the data's integrity and preventing " +
                        "unauthorized changes.", R.drawable.blockhain_icon);
            case 1:
                return EducationFragment.newInstance("What is a Rugpull?", "A rugpull is a type of scam that occurs in the cryptocurrency world, primarily in decentralized finance (DeFi) projects. In a rugpull, project " +
                        "developers create a seemingly legitimate project, attract investors, and then suddenly withdraw all funds by removing liquidity, leaving investors with worthless tokens. This fraudulent activity is called a rugpull " +
                        "because it is analogous to someone pulling a rug out from under someone, causing them to fall.", R.drawable.rugpull_icon);
            case 2:
                return EducationFragment.newInstance("Hardware Wallets", "Hardware wallets are physical devices that securely store a user's private keys for cryptocurrencies offline. These wallets are considered more secure than " +
                        "software wallets because they are less susceptible to hacking, phishing attacks, and other online threats. By storing the private keys offline, hardware wallets provide an extra layer of security known as \"cold storage\" that " +
                        "protects users from losing their funds.", R.drawable.wallet_icon);
            case 3:
                return EducationFragment.newInstance("Proof of Stake vs Proof of Work", "Proof of Stake (PoS) and Proof of Work (PoW) are two different consensus algorithms used in blockchain networks to validate transactions and create new blocks. " +
                        "PoW requires miners to solve complex mathematical puzzles to validate transactions and add new blocks to the blockchain. This process requires significant computational power and energy consumption. PoS, on the other hand, allows validators to create new blocks and validate transactions based on the number of tokens they hold (their \"stake\") and other factors. " +
                        "PoS is considered more energy-efficient and secure than PoW as it doesn't require massive amounts of computational power.", R.drawable.proof_of_work_icon);
            case 4:
                return EducationFragment.newInstance("NFTs", "NFTs, or Non-Fungible Tokens, are unique digital assets that represent ownership of a specific item or piece of content, such as art, " +
                        "collectibles, music, or in-game items. Unlike cryptocurrencies like Bitcoin, which are fungible and interchangeable, NFTs are unique and cannot be exchanged on a one-to-one basis. NFTs are typically created using " +
                        "blockchain technology, which provides a decentralized and transparent way of verifying ownership and provenance.", R.drawable.nft_icon);
            default:
                return EducationFragment.newInstance("404 Fragment not Found", "Error", R.drawable.error);

        }
    }

    @Override
    public int getItemCount() {
        // TODO: return the total size of arraylist
        return 5;
    }
}
